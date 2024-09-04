package org.example.pay.service

import org.example.pay.dto.SettlementDetailResponseDto
import org.example.pay.repository.SettlementRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 정산금 납부 서비스
 *
 */
@Service
@Transactional(readOnly = true)
class RemitSettlementService @Autowired constructor(
    private val settlementRepository: SettlementRepository,
    private val userService: UserService
) {


    /**
     * 정산해야 할 목록 조회
     *
     */
    fun findSettlementToPaid(requestedPersonId: Long): List<SettlementDetailResponseDto> {
        val settlementDetails = settlementRepository.findSettlementDetails(requestedPersonId)
        val results = mutableListOf<SettlementDetailResponseDto>()
        settlementDetails.forEach { detail ->
            val settlement = settlementRepository.findSettlementById(detail.id.value)
            val requester = userService.findUser(settlement.requesterId)
            if (!settlement.completed) {
                results.add(
                    SettlementDetailResponseDto(
                        settlementId = settlement.id.value,
                        requestName = settlement.requestName,
                        requesterId = requester.id.value,
                        requesterName = requester.name,
                        settlementDetailId = detail.id.value,
                        amount = detail.amount,
                        completed = detail.completed,
                        completionDateTime = detail.completionDateTime
                    )
                )
            }

        }
        return results
    }


    /**
     * 정산금 납부
     *
     * @param settlementDetailId 납부할 아이디
     */
    fun remitSettlement(settlementDetailId: Long) {
        val settlementDetail = settlementRepository.findSettlementDetailById(settlementDetailId)
        if (settlementDetail.completed) {
            throw NoSuchElementException("이미 정산 완료된 항목입니다.")
        }
        settlementRepository.remitSettlement(settlementDetail)

        checkSettlementIsCompleted(settlementDetail.settlementId)
    }

    fun checkSettlementIsCompleted(settlementId: Long) {
        val settlement = settlementRepository.findSettlementById(settlementId)
        if (settlement.completed) {
            return
        }
        val settlementDetails = settlementRepository.findSettlementDetailsBySettlementId(settlementId)
        val listOfNeedToPaid = settlementDetails.filter {
            !it.completed
        }.toList()
        if (listOfNeedToPaid.isEmpty()) {
            settlementRepository.updateToCompleted(settlement)
        }
        return
    }
}
