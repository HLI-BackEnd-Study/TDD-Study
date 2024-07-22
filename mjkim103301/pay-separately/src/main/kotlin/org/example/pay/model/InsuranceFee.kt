package org.example.pay.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.ToString
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@ToString
class InsuranceFee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long,
    val premium: BigDecimal,
    val paymentCompleted: Boolean = false,
    val completedDateTime: LocalDateTime? = null
)
