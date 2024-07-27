package org.example.pay.dto

import java.math.BigDecimal

class RequestedSettlementDetailDto (
    val requesterId:Long,
    val amount:BigDecimal,
    val requestedPersonId:Long
)
