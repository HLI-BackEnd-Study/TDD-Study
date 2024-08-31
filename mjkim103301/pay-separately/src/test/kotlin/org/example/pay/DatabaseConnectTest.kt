package org.example.pay

import org.example.pay.domain.table.Accounts
import org.example.pay.domain.table.InsuranceFees
import org.example.pay.domain.table.SettlementDetails
import org.example.pay.domain.table.Settlements
import org.example.pay.domain.table.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class DatabaseConnectTest {
    @BeforeAll
    fun setup() {
        connectToDatabase()
        createTables()
    }

    @AfterAll
    fun teardown() {
        transaction {
            drop(SettlementDetails)
            drop(Settlements)
            drop(Accounts)
            drop(InsuranceFees)
            drop(Users)
        }
    }

    private fun connectToDatabase() {
        Database.connect("jdbc:h2:mem:review;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
    }

    private fun createTables() {
        transaction {
            create(Users)
            create(Accounts)
            create(InsuranceFees)
            create(Settlements)
            create(SettlementDetails)
        }
    }
}
