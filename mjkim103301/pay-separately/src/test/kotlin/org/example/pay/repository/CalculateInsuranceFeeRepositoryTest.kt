package org.example.pay.repository

import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.model.InsuranceFee
import org.junit.jupiter.api.Assertions.assertEquals
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

    @Test
    fun `보험료 조회 테스트`() {
        val entity = InsuranceFee(userId = 1, premium = BigDecimal.valueOf(30000))
        val result = repository.save(entity)

        assertSoftly {
            assertThat(result.userId).isEqualTo(entity.userId)
            assertThat(result.premium).isEqualTo(entity.premium)
            assertThat(result.paymentCompleted).isEqualTo(entity.paymentCompleted)
            assertThat(result.completedDateTime).isEqualTo(entity.completedDateTime)
        }
    }
}
