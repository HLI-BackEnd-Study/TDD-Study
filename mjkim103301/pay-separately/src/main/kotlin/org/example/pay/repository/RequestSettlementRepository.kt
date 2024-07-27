package org.example.pay.repository

import org.example.pay.model.RequestedSettlement
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RequestSettlementRepository : JpaRepository<RequestedSettlement, Long> {
    fun findByRequesterId(requesterId: Long): Optional<RequestedSettlement>
}
