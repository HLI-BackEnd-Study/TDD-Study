package org.example.pay.repository

import org.example.pay.domain.model.Settlement
import org.example.pay.domain.model.SettlementDetail
import org.example.pay.domain.table.SettlementDetails
import org.example.pay.domain.table.Settlements
import org.example.pay.dto.SettlementDto
import org.example.pay.util.LocalDateTimeUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction


interface RequestSettlementRepository {
    fun createSettlement(settlementDto: SettlementDto): Settlement
    fun findSettlements(requesterId: Long): List<Settlement>
    fun findSettlementByInsuranceFeeId(insuranceFeeId: Long): Settlement
    fun findSettlementDetailsBySettlementId(settlementId: Long): List<SettlementDetail>
    fun updateToCompleted(settlement: Settlement)
}

class RequestSettlementRepositoryImpl : RequestSettlementRepository {
    override fun createSettlement(settlementDto: SettlementDto): Settlement {
        return transaction {
            val settlement = Settlement.new {
                requestName = settlementDto.requestName
                requesterId = settlementDto.requesterId
                amount = settlementDto.amount
                discountAmount = settlementDto.discountAmount
            }
            settlementDto.requestDetails.map { detail ->
                SettlementDetail.new {
                    amount = detail.amount
                    requestedPersonId = detail.requestedPersonId
                    settlementId = settlement.id.value
                }
            }
            settlement
        }
    }

    override fun findSettlements(requesterId: Long): List<Settlement> {
        return transaction {
            Settlement.find {
                Settlements.requesterId eq requesterId
            }.toList()
        }
    }

    override fun findSettlementByInsuranceFeeId(insuranceFeeId: Long): Settlement {
        return transaction {
            Settlement.find(Settlements.insuranceFeeId eq insuranceFeeId)
                .singleOrNull() ?: throw NoSuchElementException("조회할 정산금요청항목이 없습니다.")
        }
    }

    override fun findSettlementDetailsBySettlementId(settlementId: Long): List<SettlementDetail> {
        return transaction {
            SettlementDetail.find(SettlementDetails.settlementId eq settlementId)
                .toList()
        }
    }

    override fun updateToCompleted(settlement: Settlement) {
        return transaction {
            settlement.completed = true
            settlement.completionDateTime = LocalDateTimeUtils.now()
        }
    }


}
