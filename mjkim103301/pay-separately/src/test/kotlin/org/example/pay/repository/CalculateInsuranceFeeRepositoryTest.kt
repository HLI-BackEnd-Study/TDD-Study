package org.example.pay.repository

import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.model.InsuranceFee
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@DataJpaTest
class CalculateInsuranceFeeRepositoryTest() {
    @Autowired
    private lateinit var repository: CalculateInsuranceFeeRepository

    @BeforeEach
    fun setUp(){
        val entity = InsuranceFee(userId = 2, premium = BigDecimal.valueOf(30000))
        val result = repository.save(entity)
        println(result)
    }


    @Test
    fun `보험료 저장 테스트`() {
        val entity = InsuranceFee(userId = 1, premium = BigDecimal.valueOf(30000))
        val result = repository.save(entity)

        assertSoftly {
            assertThat(result.userId).isEqualTo(entity.userId)
            assertThat(result.premium).isEqualTo(entity.premium)
            assertThat(result.paymentCompleted).isEqualTo(entity.paymentCompleted)
            assertThat(result.completedDateTime).isEqualTo(entity.completedDateTime)
        }
    }


    @Test
    fun `납부가 완료되지 않은 보험료 조회 테스트`(){
        val entity = repository.findByUserIdAndPaymentCompletedIsFalse(2)
            .orElseThrow{NoSuchElementException("조회할 보험료가 없습니다.")}

        assertSoftly {
            assertThat(entity.userId).isEqualTo(2)
            assertThat(entity.premium).isEqualTo(BigDecimal(30_000))
            assertThat(entity.paymentCompleted).isFalse()
            assertThat(entity.completedDateTime).isNull()
        }
    }
}
