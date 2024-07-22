package org.example.pay.service

import org.example.pay.dto.InsuranceFeeDto
import org.example.pay.model.InsuranceFee
import org.example.pay.repository.CalculateInsuranceFeeRepository
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CalculateInsuranceFeeService(
    private val insuranceFeeRepository: CalculateInsuranceFeeRepository
) {


    /**
     * 보험료 조회
     */
    fun getInsuranceFee(userId: Long): InsuranceFeeDto {
        return insuranceFeeRepository.findByUserIdAndPaymentCompletedIsFalse(userId)
            .map { buildInsuranceFeeDto(it) }
            .orElseThrow { NoSuchElementException("조회할 보험료가 없습니다.") }
    }

    fun buildInsuranceFeeDto(insuranceFee: InsuranceFee) = InsuranceFeeDto(
        id = insuranceFee.id,
        userId = insuranceFee.userId,
        premium = insuranceFee.premium,
        paymentCompleted = insuranceFee.paymentCompleted,
        completedDateTime = insuranceFee.completedDateTime
    )

}
