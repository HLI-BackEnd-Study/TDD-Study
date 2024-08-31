package org.example.pay.dto

import java.math.BigDecimal
import java.time.LocalDateTime

class SettlementDto(
    val id: Long? = null,
    val requestName: String,
    val requesterId: Long,
    val amount: BigDecimal,
    val requestDateTime: LocalDateTime? = null,
    val completionDateTime: LocalDateTime? = null,
    val requestDetails: List<SettlementDetailDto>
) {

}
