package org.example.pay.domain.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal

object Users : LongIdTable("users", "id") {
    val name = varchar("name", 255)
}
