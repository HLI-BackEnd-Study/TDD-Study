package org.example.pay.model

import java.math.BigDecimal
import java.time.LocalDateTime

class SettlementRequest(
    val id: Long? = 0,
    val requestName: String,
    val requesterId:Long,
    val amount: BigDecimal,
    val requestDateTime: LocalDateTime,
    val completionDateTime: LocalDateTime? =null,
    val insuranceId:Long

    )
