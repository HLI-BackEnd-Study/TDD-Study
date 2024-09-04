package org.example.pay.service

import org.example.pay.util.CalculateSettlementUtils
import org.springframework.stereotype.Service
import java.math.BigDecimal


/**
 * 정산금 요청 전 요청할 정산금 계산하기 서비스
 *
 */
@Service
class CalculateSettlementService {

    /**
     * 보험료 1/N 하기
     *
     * @param premium 보험료
     * @param size 요청 인원
     * @return 정산금
     */
    fun calculateSettlementDivideByEqual(premium: BigDecimal, size: Int): BigDecimal {
        return CalculateSettlementUtils.calculateAmountDivideByEqual(premium, size)
    }


    /**
     * 보험료 1/N 했을 때 할인해줘야하는 최소 금액
     *
     * @param premium 보험료
     * @param size 요청 인원
     * @return 나머지 할인금액
     */
    fun calculateRemain(premium: BigDecimal, size: Int): BigDecimal {
        return CalculateSettlementUtils.calculateRemain(premium, size)
    }

}
