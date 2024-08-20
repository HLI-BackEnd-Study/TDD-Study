package org.example.pay.service

import org.example.pay.domain.model.InsuranceFee
import org.example.pay.dto.InsuranceFeeDto
import org.example.pay.domain.table.InsuranceFees
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CalculateInsuranceFeeService(
) {


    /**
     * 보험료 조회
     */
    fun getInsuranceFee(userId: Long): InsuranceFeeDto {
        val insuranceFee = transaction {
            InsuranceFees.select {
                (InsuranceFees.userId eq userId) and
                        (InsuranceFees.paymentCompleted eq false)
            }.singleOrNull()
        } ?: throw NoSuchElementException("조회할 보험료가 없습니다.")


    return buildInsuranceFeeDto(insuranceFee)
}

fun buildInsuranceFeeDto(insuranceFee: InsuranceFee) = InsuranceFeeDto(
    userId = insuranceFee.id.value,
    premium = insuranceFee.premium,
    paymentCompleted = insuranceFee.paymentCompleted,
    completedDateTime = insuranceFee.completedDateTime
)

}
