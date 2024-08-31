package org.example.pay.repository

import org.example.pay.domain.model.InsuranceFee
import org.example.pay.domain.table.InsuranceFees
import org.example.pay.dto.InsuranceFeeDto
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction


interface CalculateInsuranceFeeRepository {
    fun createInsuranceFee(insuranceFeeDto: InsuranceFeeDto): InsuranceFee
    fun findInsuranceFeeToBePaidByUserId(userId: Long): List<InsuranceFee>
}

class CalculateInsuranceFeeRepositoryImpl : CalculateInsuranceFeeRepository {
    override fun createInsuranceFee(insuranceFeeDto: InsuranceFeeDto): InsuranceFee {
        return transaction {
            InsuranceFee.new {
                userId = insuranceFeeDto.userId
                premium = insuranceFeeDto.premium
            }
        }
    }

    override fun findInsuranceFeeToBePaidByUserId(userId: Long): List<InsuranceFee> {
        return transaction {
            InsuranceFee.find {
                (InsuranceFees.userId eq userId) and
                        (InsuranceFees.paymentCompleted eq false)
            }.toList()
        }
    }

}

