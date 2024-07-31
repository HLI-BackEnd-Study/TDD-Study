package org.example.pay.model

import jakarta.persistence.*
import lombok.ToString
import java.math.BigDecimal
import java.time.LocalDateTime


@Entity
class RequestedSettlement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val requestName: String,
    val requesterId: Long,
    val amount: BigDecimal,
    val discountAmount: BigDecimal,
    val requestDateTime: LocalDateTime,
    val completionDateTime: LocalDateTime? = null,
    @OneToMany(fetch = FetchType.LAZY)
    val requestDetails: List<RequestedSettlementDetail>

) {
}
