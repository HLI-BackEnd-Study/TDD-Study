package org.example.pay.domain.table

import org.example.pay.domain.table.Accounts.autoIncrement
import org.example.pay.domain.table.Accounts.long
import org.example.pay.domain.table.Accounts.uniqueIndex
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal

object Users : LongIdTable("users", "id") {
    val name = varchar("name", 255)
}
