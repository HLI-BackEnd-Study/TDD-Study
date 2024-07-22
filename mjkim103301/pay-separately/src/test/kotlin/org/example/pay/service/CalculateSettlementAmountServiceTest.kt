package org.example.pay.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.dto.RequestedSettlementDetailDto
import org.example.pay.dto.UserDto
import org.example.pay.model.RequestedSettlement
import org.example.pay.model.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

/**
 * 정산금 계산 서비스 테스트
 */
class CalculateSettlementAmountServiceTest {
    @Test
    fun `정산금 동일한 금액으로 딱 떨어지게 계산하는 테스트`() {
        val insuranceFee: BigDecimal = BigDecimal.valueOf(120_000)
        val people: Int = 5

        val settlementAmount = insuranceFee.divide(BigDecimal(people))
        assertSoftly {
            assertThat(settlementAmount).isEqualTo(BigDecimal.valueOf(24_000))
            assertThat(insuranceFee.remainder(BigDecimal(people))).isEqualTo(BigDecimal.ZERO)
        }
    }

    @Test
    fun `정산금을 동일한 금액으로 나눌 수 없는 경우 테스트`() {
        val insuranceFee: BigDecimal = BigDecimal.valueOf(5_000)
        val people: Int = 3

        assertThrows<ArithmeticException> { insuranceFee.divide(BigDecimal(people)) }
    }

    @Test
    fun `정산금을 동일한 금액으로 나눌 수 없는 경우, 원금에 더해야 하는 최소 금액 계산하는 테스트`() {
        val insuranceFee: BigDecimal = BigDecimal.valueOf(5_000)
        val people: Int = 7

        val remain = BigDecimal(people).minus(insuranceFee.remainder(BigDecimal(people)));
        println("나머지: $remain")
        val settlement = insuranceFee.add(remain).divide(BigDecimal(people));
        println("보험료 정산금: $settlement")

        assertSoftly {
            assertThat(remain).isEqualTo(BigDecimal(5))
            assertThat(settlement.multiply(BigDecimal(people))).isEqualTo(insuranceFee.plus(remain))
        }
    }

    @Test
    fun `사람들에게 요청한 정산금의 합은 총 보험료를 넘을 수 없다`() {
        val users = listOf(
            UserDto(1, "Alice"),
            UserDto(2, "Bob"),
            UserDto(3, "Charlie"),
            UserDto(4, "Dave"),
            UserDto(5, "Minji")
        )

        // Alice -> Bob, Charlie, Dave, Minji에게 보험료 정산을 요청함.
        // Alice의 보험료는 30,000 원이며, 각 사람들에게 10,000원씩 요청함.
        // Alice가 다른 사람들한테 받은 정산금 총합: 40,000원.
        val insuranceFee = BigDecimal.valueOf(30_000)
        val requestedSettlements = listOf(
            RequestedSettlementDetailDto(
                1,
                BigDecimal(10_000),
                2
            ),
            RequestedSettlementDetailDto(
                1,
                BigDecimal(10_000),
                3
            ),
            RequestedSettlementDetailDto(
                1,
                BigDecimal(10_000),
                4
            ),
            RequestedSettlementDetailDto(
                1,
                BigDecimal(10_000),
                5
            )
        )

        var requestedSettlementAmount: BigDecimal = BigDecimal.ZERO
        for (settlement in requestedSettlements) {
            requestedSettlementAmount = requestedSettlementAmount.plus(settlement.amount)
        }
        println("총 보험료: $insuranceFee")
        println("요청한 정산금 총합 $requestedSettlementAmount")
        assertThat(insuranceFee >= requestedSettlementAmount).isFalse()
    }
}
