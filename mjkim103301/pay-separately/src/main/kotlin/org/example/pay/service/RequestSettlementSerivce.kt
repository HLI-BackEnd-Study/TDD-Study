package org.example.pay.service

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.pay.domain.model.Settlement
import org.example.pay.domain.model.SettlementDetail
import org.example.pay.dto.SettlementDto
import java.math.BigDecimal

class RequestSettlementSerivce(
) {


    /**
     * 정산 요청 저장
     */
    fun createRequestedSettlement(
        settlementDetailDto: SettlementDto,
        discount: BigDecimal
    ) {
        val settlement = Settlement.new {
            requestName = settlementDetailDto.requestName
            requesterId = settlementDetailDto.requesterId
            amount = settlementDetailDto.amount
            discountAmount = discount
            requestDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }

        settlementDetailDto.requestDetails
            .map { detail ->
                SettlementDetail.new {
                    amount = detail.amount
                    requestedPersonId = detail.requestedPersonId
                    settlementId = settlement.id.value
                }
            }
    }
}
