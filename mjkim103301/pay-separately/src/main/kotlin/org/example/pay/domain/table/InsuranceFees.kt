package org.example.pay.domain.table

import jdk.jfr.internal.handlers.EventHandler.timestamp
import org.example.pay.domain.table.Accounts.autoIncrement
import org.example.pay.domain.table.Accounts.references
import org.example.pay.domain.table.Accounts.uniqueIndex
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.math.BigDecimal

object InsuranceFees: LongIdTable("insurance_fees", "id") {
    val userId = long("user_id").references(Users.id)
    val premium = decimal("premium", 18, 0)
    val paymentCompleted = bool("payment_completed").default(false)
    val completedDateTime =datetime("completed_date_time").nullable()
}

