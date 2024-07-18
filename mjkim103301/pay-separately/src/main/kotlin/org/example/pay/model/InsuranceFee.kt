package org.example.pay.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class InsuranceFee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userId: Long = 0,
    val premium: BigDecimal? = null,
    val paymentCompleted: Boolean = false,
    val completedDateTime: LocalDateTime? = null
)
