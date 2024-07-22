package org.example.pay.dto

import java.math.BigDecimal
import java.time.LocalDateTime

class InsuranceFeeDto(
    val id: Long? = null,
    val userId: Long,
    val premium: BigDecimal,
    val paymentCompleted: Boolean,
    val completedDateTime: LocalDateTime? = null
)
