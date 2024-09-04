package org.example.pay.dto.request

import kotlinx.datetime.LocalDateTime
import java.math.BigDecimal

data class SettlementCreateDto(
    val requestName: String,
    val requesterId: Long,
    val insuranceFeeId: Long,
    val amount: BigDecimal,
    val discountAmount : BigDecimal,
    val requestDateTime: LocalDateTime? = null,
    val completed : Boolean = false,
    val completionDateTime: LocalDateTime? = null,
    val requestDetails: List<SettlementDetailCreateDto>
) {

}
