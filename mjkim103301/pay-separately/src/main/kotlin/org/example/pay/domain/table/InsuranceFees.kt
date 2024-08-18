package org.example.pay.domain.table

import jdk.jfr.internal.handlers.EventHandler.timestamp
import org.example.pay.domain.table.Accounts.autoIncrement
import org.example.pay.domain.table.Accounts.references
import org.example.pay.domain.table.Accounts.uniqueIndex
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.math.BigDecimal

object InsuranceFees: Table("insurance_fees") {
    val id = long("id")
    val userId = long("user_id").references(Users.id)
    val premium = BigDecimal("premium")
    val paymentCompleted = bool("payment_completed").default(false)
    val completedDateTime =datetime("completed_date_time")
}

