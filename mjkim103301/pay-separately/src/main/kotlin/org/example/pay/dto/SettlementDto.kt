package org.example.pay.dto

import kotlinx.datetime.LocalDateTime
import java.math.BigDecimal


data class SettlementDto(
    val id: Long,
    val requestName: String,
    val requesterId: Long,
    val insuranceFeeId: Long,
    val amount: BigDecimal,
    val discountAmount : BigDecimal,
    val requestDateTime: LocalDateTime? = null,
    val completed : Boolean = false,
    val completionDateTime: LocalDateTime? = null,
    val requestDetails: List<SettlementDetailDto>
) {

}
