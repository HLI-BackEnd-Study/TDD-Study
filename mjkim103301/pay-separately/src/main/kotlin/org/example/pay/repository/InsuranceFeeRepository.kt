package org.example.pay.repository

import org.example.pay.domain.model.InsuranceFee
import org.example.pay.domain.table.InsuranceFees
import org.example.pay.dto.InsuranceFeeDto
import org.example.pay.util.LocalDateTimeUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository


interface InsuranceFeeRepository {
    fun createInsuranceFee(insuranceFeeDto: InsuranceFeeDto): InsuranceFee
    fun findInsuranceFeeToBePaid(userId: Long): List<InsuranceFee>
    fun findById(insuranceFeeId: Long): InsuranceFee
    fun updateToCompleted(insuranceFee: InsuranceFee)

}

@Repository
class InsuranceFeeRepositoryImpl : InsuranceFeeRepository {
    override fun createInsuranceFee(insuranceFeeDto: InsuranceFeeDto): InsuranceFee {
        return  transaction {
            InsuranceFee.new {
                userId = insuranceFeeDto.userId
                premium = insuranceFeeDto.premium
            }
        }
    }

    override fun findInsuranceFeeToBePaid(userId: Long): List<InsuranceFee> {
        return transaction {
            InsuranceFee.find {
                (InsuranceFees.userId eq userId) and
                        (InsuranceFees.paymentCompleted eq false)
            }.toList()
        }
    }

    override fun findById(insuranceFeeId: Long): InsuranceFee {
        return transaction {
            InsuranceFee.findById(insuranceFeeId) ?: throw NoSuchElementException("조회할 보험료가 없습니다.")
        }
    }

    override fun updateToCompleted(insuranceFee: InsuranceFee) {
        return transaction {
            insuranceFee.paymentCompleted = true
            insuranceFee.completedDateTime = LocalDateTimeUtils.now()
        }
    }
}

