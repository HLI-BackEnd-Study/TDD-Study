package org.example.pay.domain.model

import org.example.pay.domain.table.Settlements
import org.example.pay.dto.SettlementDto
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.math.BigDecimal

class Settlement(id: EntityID<Long>) : LongEntity(id) {


    companion object : LongEntityClass<Settlement>(Settlements)

    var requestName by Settlements.requestName
    var requesterId by Settlements.requesterId
    var insuranceFeeId by Settlements.insuranceFeeId
    var amount by Settlements.amount
    var discountAmount by Settlements.discountAmount
    var requestDateTime by Settlements.requestDateTime
    var completed by Settlements.completed
    var completionDateTime by Settlements.completionDateTime

    fun buildSettlementDto(): SettlementDto {
        return SettlementDto(
            id = this.id.value,
            requestName = this.requestName,
            requesterId = this.requesterId,
            insuranceFeeId = this.insuranceFeeId,
            amount = this.amount,
            discountAmount = this.discountAmount ?: BigDecimal.ZERO,
            requestDateTime = this.requestDateTime,
            completed = this.completed,
            completionDateTime = this.completionDateTime,
            requestDetails = emptyList(),
        )
    }
}
