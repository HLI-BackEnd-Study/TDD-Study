package org.example.pay.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class RequestedSettlementDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val amount: BigDecimal,
    val requestedPersonId: Long,
    var isCompleted: Boolean = false,
    val completionDateTime: LocalDateTime? = null,
)
