package org.example.pay.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class SettlementDetail (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    val request: SettlementRequest,
    val amount: BigDecimal,
    val requestedPersonId:Long,
    var isCompleted:Boolean = false,
    val completionDateTime: LocalDateTime? = null,
)
