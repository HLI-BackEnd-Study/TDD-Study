package org.example.pay.domain.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object SettlementDetails : LongIdTable("settlement_details", "id") {
    val amount = decimal("amount", 18, 0)
    val requestedPersonId = long("requested_person_id").references(Users.id)
    val completed = bool("completed")
    val completionDateTime = datetime("completion_date_time")
    val settlementId = long("settlement_id").references(Settlements.id)
}
