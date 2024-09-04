package org.example.pay.service

import org.example.pay.dto.InsuranceFeeDto
import org.example.pay.repository.InsuranceFeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 개인 보험료 관리 서비스
 *
 */

@Service
@Transactional(readOnly = true)
class InsuranceFeeService @Autowired constructor(
    private val insuranceFeeRepository: InsuranceFeeRepository,
    private val requestSettlementSerivce: RequestSettlementSerivce

) {

    /**
     * 내야 할 보험료 목록 조회
     */
    fun findInsuranceFee(userId: Long): List<InsuranceFeeDto> {
        val insuranceFees = insuranceFeeRepository.findInsuranceFeeToBePaid(userId)
        return insuranceFees.map {
            it.toDto()
        }
    }


    /**
     * 보험료 납부 완료 확인
     */
    @Transactional
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
