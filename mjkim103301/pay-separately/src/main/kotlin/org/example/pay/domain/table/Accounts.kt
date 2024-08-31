package org.example.pay.domain.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Table.Dual.integer
import org.jetbrains.exposed.sql.Table.Dual.long
import java.math.BigDecimal

object Accounts : LongIdTable("accounts", "id") {
    val userId = long("user_id").uniqueIndex()
        .references(Users.id)
    val balance = decimal("balance", 18, 0).default(BigDecimal.ZERO)
}
