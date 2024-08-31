package org.example.pay.repository

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.DatabaseConnectTest
import org.example.pay.domain.model.InsuranceFee
import org.example.pay.domain.model.User
import org.example.pay.dto.InsuranceFeeDto
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

/**
 * 보험료 계산 레포지토리 테스트
 */
class CalculateInsuranceFeeRepositoryTest() : DatabaseConnectTest() {
    private val repository: CalculateInsuranceFeeRepository = CalculateInsuranceFeeRepositoryImpl()

    @BeforeEach
    fun setUp(){
        transaction {
            User.new {
                name = "홍길동"
            }
            User.new{
                name = "심청이"
            }
            InsuranceFee.new {
                userId =2
                premium = BigDecimal.valueOf(30_000)
            }
        }

    }


    @Test
    fun `보험료 저장 테스트`() {

        val insuranceFeeDto = InsuranceFeeDto(
            userId = 1,
            premium = BigDecimal.valueOf(30_000)
        )
        val result = repository.createInsuranceFee(insuranceFeeDto)

        assertSoftly {
            assertThat(result.userId).isEqualTo(1)
            assertThat(result.premium).isEqualTo(BigDecimal.valueOf(30_000))
            assertThat(result.paymentCompleted).isEqualTo(false)
            assertThat(result.completedDateTime).isNull()
        }
    }


    @Test
    fun `납부가 완료되지 않은 보험료 조회 테스트`(){
        val userId:Long = 2
        val insurances = repository.findInsuranceFeeToBePaidByUserId(userId)

        assertSoftly {
            insurances.forEach{
                assertThat(it.userId).isEqualTo(2)
                assertThat(it.premium).isEqualTo(BigDecimal(30_000))
                assertThat(it.paymentCompleted).isFalse()
                assertThat(it.completedDateTime).isNull()
            }
        }
    }
}
