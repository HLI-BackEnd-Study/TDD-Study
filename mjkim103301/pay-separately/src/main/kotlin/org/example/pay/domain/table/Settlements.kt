package org.example.pay.domain.table

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Settlements: LongIdTable("settlements", "id") {
    val requestName = varchar("request_name", 100)
    val requesterId = long("requester_id").references(Users.id)
    val amount = decimal("amount", 18, 0)
    val discountAmount = decimal("discount", 18, 0).nullable()
    val requestDateTime = datetime("request_date_time").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()) }
    val completionDateTime = datetime("completion_date_time").nullable()
}
