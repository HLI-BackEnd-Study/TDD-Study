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
        val remain = premium.remainder(BigDecimal(size))
        return premium.minus(remain).divide(BigDecimal(size))
    }

    /**
     * 요청받은 각기 다른 정산금액의 총합이 보험료와 같은지 계산하기
     */
    fun calculateDifferentAmount(premium: BigDecimal, remiterAmountList: List<BigDecimal>): Boolean {
        val totalAmount: BigDecimal = remiterAmountList.stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add)

        return totalAmount == premium
    }
}
