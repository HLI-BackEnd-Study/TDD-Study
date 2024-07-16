package org.example.pay.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class CalculateSettlementAmount {
    @Test
    fun `정산금 동일한 금액으로 딱 떨어지게 계산하는 테스트`(){
        val insuranceFee : BigDecimal = BigDecimal.valueOf(120_000)
        val people : Int = 5

        val settlementAmount = insuranceFee.divide(BigDecimal(people))
        assertSoftly{
            assertThat(settlementAmount).isEqualTo(BigDecimal.valueOf(24_000))
            assertThat(insuranceFee.remainder(BigDecimal(people))).isEqualTo(BigDecimal.ZERO)
        }
    }

    @Test
    fun `정산금을 동일한 금액으로 나눌 수 없는 경우 테스트`(){
        val insuranceFee : BigDecimal = BigDecimal.valueOf(5_000)
        val people : Int = 3

        assertThrows<ArithmeticException> { insuranceFee.divide(BigDecimal(people)) }
    }

//    @Test
//    fun `정산금을 동일한 금액으로 나눌 수 없는 경우, 원금에 더해야 하는 최소 금액 계산하는 테스트`(){
//        val insuranceFee : BigDecimal = BigDecimal.valueOf(5_000)
//        val people : Int = 3
//
//        val settlementAmount = insuranceFee.divide(BigDecimal(people))
//    }
}
