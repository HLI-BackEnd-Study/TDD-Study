package org.example.pay.dto.request

import java.math.BigDecimal

data class SettlementDetailCreateDto(
    val amount: BigDecimal,
    val requestedPersonId: Long
)
