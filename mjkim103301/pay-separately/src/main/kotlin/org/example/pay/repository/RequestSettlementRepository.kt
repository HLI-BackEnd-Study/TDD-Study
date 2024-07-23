package org.example.pay.repository

import org.example.pay.model.RequestedSettlement
import org.springframework.data.jpa.repository.JpaRepository

interface RequestSettlementRepository : JpaRepository<RequestedSettlement, Long> {
}
