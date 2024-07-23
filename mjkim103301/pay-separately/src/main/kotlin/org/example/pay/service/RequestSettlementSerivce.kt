package org.example.pay.service

import org.example.pay.dto.RequestedSettlementDetailDto
import org.example.pay.model.RequestedSettlement
import org.example.pay.repository.RequestSettlementRepository

class RequestSettlementSerivce(
    private val requestSettlementRepository: RequestSettlementRepository
) {


    fun createRequestedSettlement(requestedSettlementDetailDto: RequestedSettlementDetailDto) {
        val requestedSettlement = buildRequestedSettlement(requestedSettlementDetailDto)
        requestSettlementRepository.save(requestedSettlement)
    }

    private fun buildRequestedSettlement(requestedSettlementDetailDto: RequestedSettlementDetailDto): RequestedSettlement {
        // TODO 완성하기
        return RequestedSettlement()
    }


}
