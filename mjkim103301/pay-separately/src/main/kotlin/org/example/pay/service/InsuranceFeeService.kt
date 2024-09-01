package org.example.pay.service

import org.example.pay.domain.model.InsuranceFee
import org.example.pay.dto.InsuranceFeeDto
import org.example.pay.repository.InsuranceFeeRepository
import org.example.pay.repository.InsuranceFeeRepositoryImpl
import org.springframework.stereotype.Service

/**
 * 개인 보험료 관리 서비스
 *
 */
@Service
class InsuranceFeeService(
    private val insuranceFeeRepository: InsuranceFeeRepository = InsuranceFeeRepositoryImpl()

) {
    private val requestSettlementSerivce: RequestSettlementSerivce = RequestSettlementSerivce()

    /**
     * 내야 할 보험료 목록 조회
     */
    fun getInsuranceFee(userId: Long): List<InsuranceFeeDto> {
        val insuranceFees = insuranceFeeRepository.findInsuranceFeeToBePaid(userId)
        return insuranceFees.map {
            buildInsuranceFeeDto(it)
        }
    }

    fun buildInsuranceFeeDto(insuranceFee: InsuranceFee) = InsuranceFeeDto(
        userId = insuranceFee.id.value,
        premium = insuranceFee.premium,
        paymentCompleted = insuranceFee.paymentCompleted,
        completedDateTime = insuranceFee.completedDateTime
    )

    /**
     * 보험료 납부 완료 확인
     */
    fun checkInsuranceFeePaymentIsFinished(insuranceFeeId: Long): Boolean {
        val insuranceFee = insuranceFeeRepository.findById(insuranceFeeId)
        if (insuranceFee.paymentCompleted) {
            return true
        }
        if (requestSettlementSerivce.checkSettlementIsCompleted(insuranceFeeId)) {
            insuranceFeeRepository.updateToCompleted(insuranceFee)
            return true
        }
        return false
    }
}
