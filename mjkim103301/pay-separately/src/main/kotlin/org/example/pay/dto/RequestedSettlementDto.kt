package org.example.pay.dto

import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import org.example.pay.model.RequestedSettlementDetail
import java.math.BigDecimal
import java.time.LocalDateTime

class RequestedSettlementDto (
    val id:Long? = null,
    val requestName: String,
    val requesterId: Long,
    val amount: BigDecimal,
    val requestDateTime: LocalDateTime,
    val completionDateTime: LocalDateTime? = null,
    val requestDetails: List<RequestedSettlementDetailDto>
){

}
