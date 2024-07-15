package org.example.pay.model

import java.math.BigDecimal
import java.time.LocalDateTime

class SettlementDetail (
    val id:Long = 0,
    val request: SettlementRequest,
    val amount: BigDecimal,
    val requestedPersonId:Long,
    var isCompleted:Boolean = false,
    var completionDateTime: LocalDateTime? = null,
)
