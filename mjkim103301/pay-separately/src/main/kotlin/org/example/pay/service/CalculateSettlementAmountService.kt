package org.example.pay.service

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CalculateSettlementAmountService(
) {

    /**
     * 동일한 정산금액 계산하기 요청
     */
    fun calculateAmountDivideByEqual(premium: BigDecimal, size: Int): BigDecimal {
        validateOverOrSameToZero(premium)
        val remain = premium.remainder(BigDecimal(size))
        return premium.minus(remain).divide(BigDecimal(size))
    }

    /**
     * 요청받은 각기 다른 정산금액의 총합이 보험료와 같은지 계산하기
     */
    fun calculateDifferentAmount(premium: BigDecimal, remitterAmountList: List<BigDecimal>): Boolean {
        validateOverOrSameToZero(premium)
        remitterAmountList.forEach {
            validateOverOrSameToZero(it)
        }
        val totalAmount: BigDecimal = remitterAmountList
            .fold(BigDecimal.ZERO, BigDecimal::add)

        return totalAmount == premium
    }

    fun validateOverOrSameToZero(money: BigDecimal) {
        if (money < BigDecimal.ZERO) {
            throw IllegalArgumentException("0원 미만인 금액이 요청으로 들어올 수 없습니다.")
        }
    }
}
