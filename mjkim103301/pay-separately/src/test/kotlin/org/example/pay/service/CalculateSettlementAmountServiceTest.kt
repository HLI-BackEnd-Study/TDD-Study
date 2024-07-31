package org.example.pay.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.dto.InsuranceFeeDto
import org.example.pay.dto.RequestedSettlementDetailDto
import org.example.pay.dto.UserDto
import org.example.pay.model.RequestedSettlement
import org.example.pay.model.User
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

/**
 * 정산금 계산 서비스 테스트
 */
class CalculateSettlementAmountServiceTest() {
    private lateinit var settlementAmountService: CalculateSettlementAmountService

    @BeforeEach
    fun setUp() {
        settlementAmountService = CalculateSettlementAmountService()
    }

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
    fun `정산금을 동일한 금액으로 나눌 수 없는 경우, 최소 한화생명이 지급하는 금액 계산하는 테스트`() {
        val insuranceFee: BigDecimal = BigDecimal.valueOf(5_000)
        val people: Int = 7

        val remain = settlementAmountService.calculateRemain(insuranceFee, people)
        println("나머지: $remain")
        val settlement = settlementAmountService.calculateAmountDivideByEqual(insuranceFee, people)
        println("보험료 정산금: $settlement")

        assertSoftly {
            assertThat(settlement.multiply(BigDecimal(people)).plus(remain)).isEqualTo(insuranceFee)
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


    @Test
    fun `송금인에게 요청할 정산금(총 보험료를 동일한 금액으로 나눔)을 계산한다`() {
        val ownerInsuranceFee =
            InsuranceFeeDto(id = 1, userId = 1, premium = BigDecimal(10_000), paymentCompleted = false)

        val remitters = listOf(
            UserDto(1, "Alice"),
            UserDto(2, "Bob"),
            UserDto(3, "Charlie"),
            UserDto(4, "David")
        )

        val amount = settlementAmountService.calculateAmountDivideByEqual(ownerInsuranceFee.premium, remitters.size)
        assertThat(ownerInsuranceFee.premium >= amount.multiply(BigDecimal(remitters.size))).isTrue()
    }

    @Test
    fun `동일한 금액으로 나눌 수 없으면, 동일한 금액으로 나눌 수 있는 최소 금액을 한화생명이 지급한다`() {
        val ownerInsuranceFee =
            InsuranceFeeDto(id = 1, userId = 1, premium = BigDecimal(10_000), paymentCompleted = false)

        val remitters = listOf(
            UserDto(1, "Alice"),
            UserDto(2, "Bob"),
            UserDto(3, "Charlie"),
            UserDto(4, "David")
        )

        val amount = settlementAmountService.calculateAmountDivideByEqual(ownerInsuranceFee.premium, remitters.size)
        val remain = settlementAmountService.calculateRemain(ownerInsuranceFee.premium, remitters.size)

        assertThat(ownerInsuranceFee.premium).isEqualTo(amount.multiply(BigDecimal(remitters.size)).add(remain))
    }


    @Test
    fun `각 송금인에게 요청할 정산금을 계산한다`() {
        val ownerInsuranceFee =
            InsuranceFeeDto(id = 1, userId = 1, premium = BigDecimal(30_000), paymentCompleted = false)

        val remitters = listOf(
            RequestedSettlementDetailDto(1, BigDecimal(15_000), 1),
            RequestedSettlementDetailDto(1, BigDecimal(5_000), 2),
            RequestedSettlementDetailDto(1, BigDecimal(5_000), 3),
            RequestedSettlementDetailDto(1, BigDecimal(5_000), 4)
        )

        val remitterAmountList = remitters.stream()
            .map { it.amount }
            .toList()

        val possible: Boolean =
            settlementAmountService.calculateDifferentAmount(ownerInsuranceFee.premium, remitterAmountList)

        assertTrue(possible)
    }

    @Test
    fun `송금인에게 요청하는 금액은 0원 이상이어야 한다`() {
        val ownerInsuranceFee =
            InsuranceFeeDto(id = 1, userId = 1, premium = BigDecimal(30_000), paymentCompleted = false)

        val remitters = listOf(
            RequestedSettlementDetailDto(1, BigDecimal(15_000), 1),
            RequestedSettlementDetailDto(1, BigDecimal(5_000), 2),
            RequestedSettlementDetailDto(1, BigDecimal(6_000), 3),
            RequestedSettlementDetailDto(1, BigDecimal(-1_000), 4)
        )

        val remitterAmountList = remitters.stream()
            .map { it.amount }
            .toList()

        assertThrows<IllegalArgumentException> {
            settlementAmountService.calculateDifferentAmount(
                ownerInsuranceFee.premium,
                remitterAmountList
            )
        }
    }


    @Test
    fun `정산금을 보내야 하는 사람은 1명 이상이어야 한다`() {
        val ownerInsuranceFee =
            InsuranceFeeDto(id = 1, userId = 1, premium = BigDecimal(30_000), paymentCompleted = false)

        val remitters = ArrayList<RequestedSettlementDetailDto>()

        val remitterAmountList = remitters.stream()
            .map { it.amount }
            .toList()

        assertThrows<IllegalArgumentException> {
            settlementAmountService.calculateDifferentAmount(
                ownerInsuranceFee.premium,
                remitterAmountList
            )
        }
    }
}
