package org.example.pay.domain.model


import org.example.pay.domain.model.Account.Companion.table
import org.example.pay.domain.table.Accounts
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import java.math.BigDecimal


class Account(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Account>(Accounts)
    var userId by Accounts.userId
    var balance by Accounts.balance
}
