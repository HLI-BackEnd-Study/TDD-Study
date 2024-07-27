package org.example.pay.service

import org.example.pay.dto.RequestedSettlementDetailDto
import org.example.pay.dto.RequestedSettlementDto
import org.example.pay.model.RequestedSettlement
import org.example.pay.model.RequestedSettlementDetail
import org.example.pay.repository.RequestSettlementRepository
import java.time.LocalDateTime

class RequestSettlementSerivce(
    private val requestSettlementRepository: RequestSettlementRepository
) {


    /**
     * 정산 요청 저장
     */
    fun createRequestedSettlement(settlementDetailDto: RequestedSettlementDto) {
        val requestedSettlement = buildRequestedSettlement(settlementDetailDto)
        requestSettlementRepository.save(requestedSettlement)
    }

    private fun buildRequestedSettlement(requestedSettlementDto: RequestedSettlementDto): RequestedSettlement {
        val requestDetailDtos = requestedSettlementDto.requestDetails
        val requestDetails = buildRequestedSettlementDetails(requestDetailDtos)
        return RequestedSettlement(
            requestName = requestedSettlementDto.requestName,
            requesterId = requestedSettlementDto.requesterId,
            amount = requestedSettlementDto.amount,
            requestDateTime = LocalDateTime.now(),
            requestDetails = requestDetails
        )
    }

    private fun buildRequestedSettlementDetails(requestedDetailDtos: List<RequestedSettlementDetailDto>): List<RequestedSettlementDetail> {
        return requestedDetailDtos.stream()
            .map(this::buildRequestedSettlementDetail)
            .toList()
    }

    private fun buildRequestedSettlementDetail(requestedSettlementDetailDto: RequestedSettlementDetailDto) =
        RequestedSettlementDetail(
            amount = requestedSettlementDetailDto.amount,
            requestedPersonId = requestedSettlementDetailDto.requestedPersonId
        )


}
