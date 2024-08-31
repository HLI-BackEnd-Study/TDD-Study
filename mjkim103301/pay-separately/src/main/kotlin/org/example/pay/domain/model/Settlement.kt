package org.example.pay.domain.model

import org.example.pay.domain.table.Settlements
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Settlement(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Settlement>(Settlements)
    var requestName by Settlements.requestName
    var requesterId by Settlements.requesterId
    var amount by Settlements.amount
    var discountAmount by Settlements.discountAmount
    var requestDateTime by Settlements.requestDateTime
    var completionDateTime by Settlements.completionDateTime
}
