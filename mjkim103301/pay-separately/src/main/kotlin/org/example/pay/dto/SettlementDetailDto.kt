package org.example.pay.dto

import java.math.BigDecimal

data class SettlementDetailDto(
    val id: Long,
    val amount: BigDecimal,
    val requestedPersonId: Long
)
