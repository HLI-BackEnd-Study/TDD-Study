package org.example.pay.repository

import org.example.pay.model.RequestedSettlement
import org.example.pay.model.RequestedSettlementDetail
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RemitSettlementRepository : JpaRepository<RequestedSettlementDetail, Long> {
}
