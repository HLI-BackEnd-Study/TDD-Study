package org.example.pay.repository

import org.example.pay.domain.model.SettlementDetail
import org.springframework.data.jpa.repository.JpaRepository

interface RemitSettlementRepository : JpaRepository<SettlementDetail, Long> {
}
