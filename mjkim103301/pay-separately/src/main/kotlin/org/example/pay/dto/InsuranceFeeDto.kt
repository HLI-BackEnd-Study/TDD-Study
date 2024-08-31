package org.example.pay.dto

import kotlinx.datetime.LocalDateTime
import java.math.BigDecimal

data class InsuranceFeeDto(
    val userId: Long,
    val premium: BigDecimal,
    val paymentCompleted: Boolean = false,
    val completedDateTime: LocalDateTime? = null
)
