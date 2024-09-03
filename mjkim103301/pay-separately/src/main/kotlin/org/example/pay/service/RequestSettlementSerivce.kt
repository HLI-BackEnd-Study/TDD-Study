package org.example.pay.service

import org.example.pay.domain.model.Settlement
import org.example.pay.dto.SettlementDto
import org.example.pay.dto.request.SettlementCreateDto
import org.example.pay.repository.RequestSettlementRepository
import org.example.pay.util.CalculateSettlementUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 정산금 관리 서비스
 *
 * @property requestSettlementRepository
 */
@Service
@Transactional(readOnly = true)
class RequestSettlementSerivce(
    private val requestSettlementRepository: RequestSettlementRepository
) {
    /**
     * 정산 요청 저장
     */
    @Transactional
    fun createRequestedSettlements(
        settlementDto: SettlementCreateDto
    ): SettlementDto {
        val requestedAmounts = settlementDto.requestDetails.map {
            it.amount
        } + (settlementDto.discountAmount)
        val isPossible = CalculateSettlementUtils.isSameToPremium(settlementDto.amount, requestedAmounts)
        require(isPossible) { "정산금 총합이 최종 금액과 일치하지 않습니다." }

        val settlement = requestSettlementRepository.createSettlement(settlementDto)
        val settlementDetailDto = requestSettlementRepository.findSettlementDetailsBySettlementId(settlement.id.value)
            .map { it.toDto() }
            .toList()

        return settlement.toDto(settlementDetailDto)
    }

    /**
     * 정산 완료 확인
     *
     * @param insuranceFeeId 보험료 아이디
     * @return 보험료 정산 완료 여부
     */
    @Transactional
    fun checkSettlementIsCompleted(insuranceFeeId: Long): Boolean {
        val settlement = findByInsuranceFeeId(insuranceFeeId)
        if (settlement.completed) {
            return true
        }
        val settlementDetails = requestSettlementRepository.findSettlementDetailsBySettlementId(settlement.id.value)
        val listOfNeedToPaid = settlementDetails.filter {
            !it.completed
        }.toList()
        if (listOfNeedToPaid.isEmpty()) {
            requestSettlementRepository.updateToCompleted(settlement)
            return true
        }

        return false
    }

    private fun findByInsuranceFeeId(insuranceFeeId: Long): Settlement {
        return requestSettlementRepository.findSettlementByInsuranceFeeId(insuranceFeeId)

    }

    /**
     * 내가 요청한 정산 목록 조회
     *
     * @param requesterId
     * @return
     */
    private fun findRequestSettlements(requesterId: Long): List<SettlementDto> {
        val settlements = requestSettlementRepository.findSettlements(requesterId)
        val results = mutableListOf<SettlementDto>()

        settlements.forEach { settlement ->
            val settlementDetails = requestSettlementRepository.findSettlementDetailsBySettlementId(settlement.id.value)
            val settlementDetailDtos = settlementDetails.map { detail ->
                detail.toDto()
            }.toList()
            results.add(
                settlement.toDto(settlementDetailDtos)
            )
        }
        return results
    }
}
