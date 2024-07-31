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
        validateOverToZero(size)
        val remain = calculateRemain(premium, size)
        return premium.minus(remain).divide(BigDecimal(size))
    }

    fun calculateRemain(premium: BigDecimal, size: Int): BigDecimal {
        return premium.remainder(BigDecimal(size))
    }

    /**
     * 요청받은 각기 다른 정산금액의 총합이 보험료와 같은지 계산하기
     */
    fun calculateDifferentAmount(premium: BigDecimal, remitterAmountList: List<BigDecimal>): Boolean {
        validateOverOrSameToZero(premium)
        validateOverToZero(remitterAmountList.size)
        remitterAmountList.forEach {
            validateOverOrSameToZero(it)
        }
        val totalAmount: BigDecimal = remitterAmountList
            .fold(BigDecimal.ZERO, BigDecimal::add)

        return totalAmount == premium
    }

    fun validateOverOrSameToZero(money: BigDecimal) {
        require(money >= BigDecimal.ZERO) { "0원 미만인 금액이 요청으로 들어올 수 없습니다." }
    }

    fun validateOverToZero(size: Int) {
        require(size >= 1) { "1 이상인 값만 들어올 수 있습니다." }
    }
}
