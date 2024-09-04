package org.example.pay.domain.model


import org.example.pay.domain.table.InsuranceFees
import org.example.pay.dto.InsuranceFeeDto
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class InsuranceFee(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<InsuranceFee>(InsuranceFees)

    var userId by InsuranceFees.userId
    var premium by InsuranceFees.premium
    var paymentCompleted by InsuranceFees.paymentCompleted
    var completedDateTime by InsuranceFees.completedDateTime


    fun toDto() = InsuranceFeeDto(
        userId = this.id.value,
        premium = this.premium,
        paymentCompleted = this.paymentCompleted,
        completedDateTime = this.completedDateTime
    )
}
