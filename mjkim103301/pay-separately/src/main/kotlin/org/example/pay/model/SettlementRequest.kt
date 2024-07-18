package org.example.pay.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class SettlementRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,
    val requestName: String,
    val requesterId:Long,
    val amount: BigDecimal,
    val requestDateTime: LocalDateTime,
    val completionDateTime: LocalDateTime? =null,
    val insuranceId:Long

    ) {
}
