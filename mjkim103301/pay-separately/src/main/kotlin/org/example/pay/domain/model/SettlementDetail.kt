package org.example.pay.domain.model

import org.example.pay.domain.table.SettlementDetails
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SettlementDetail(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<SettlementDetail>(SettlementDetails)

    var amount by SettlementDetails.amount
    var requestedPersonId by SettlementDetails.requestedPersonId
    var completed by SettlementDetails.completed
    var completionDateTime by SettlementDetails.completionDateTime
    var settlementId by SettlementDetails.settlementId
}
