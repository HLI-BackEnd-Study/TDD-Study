package org.example.pay.repository

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.pay.domain.model.SettlementDetail
import org.example.pay.domain.table.SettlementDetails
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction


interface RemitSettlementRepository {
    fun findSettlementDetails(requestedPersonId: Long): List<SettlementDetail>
    fun remitSettlements(settlementDetails: List<SettlementDetail>)
}

class RemitSettlementRepositoryImpl : RemitSettlementRepository {

    override fun findSettlementDetails(requestedPersonId: Long): List<SettlementDetail> {
        return transaction {
            SettlementDetail.find {
                (SettlementDetails.requestedPersonId eq requestedPersonId) and
                        (SettlementDetails.completed eq false)
            }.toList()
        }

    }

    override fun remitSettlements(settlementDetails: List<SettlementDetail>) {
        transaction {
            settlementDetails.map { detail ->
                detail.completed = true
                detail.completionDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            }
        }
    }
}
