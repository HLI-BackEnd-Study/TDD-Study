package org.example.pay.dto

import java.math.BigDecimal

class SettlementDetailDto (
    val amount:BigDecimal,
    val requestedPersonId:Long
)
