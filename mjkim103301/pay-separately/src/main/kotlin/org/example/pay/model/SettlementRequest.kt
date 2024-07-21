package org.example.pay.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class SettlementRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val requestName: String,
    val requesterId: Long,
    val amount: BigDecimal,
    val requestDateTime: LocalDateTime,
    val completionDateTime: LocalDateTime? = null,
    val insuranceId: Long,
    @OneToMany(fetch = FetchType.LAZY)
    val requestDetails: List<SettlementRequestDetail>

) {
}
