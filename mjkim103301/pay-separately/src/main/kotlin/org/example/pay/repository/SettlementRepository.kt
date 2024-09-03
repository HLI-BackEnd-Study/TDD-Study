package org.example.pay.repository

import org.example.pay.domain.model.Settlement
import org.example.pay.domain.model.SettlementDetail
import org.example.pay.domain.table.SettlementDetails
import org.example.pay.domain.table.Settlements
import org.example.pay.dto.request.SettlementCreateDto
import org.example.pay.util.LocalDateTimeUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
interface SettlementRepository {
    fun findSettlementById(settlementId: Long): Settlement
    fun findSettlementDetailById(settlementDetailId: Long): SettlementDetail
    fun findSettlementDetails(requestedPersonId: Long): List<SettlementDetail>
    fun remitSettlements(settlementDetails: List<SettlementDetail>)
    fun remitSettlement(settlementDetail: SettlementDetail)
    fun createSettlement(settlementDto: SettlementCreateDto): Settlement
    fun findSettlements(requesterId: Long): List<Settlement>
    fun findSettlementByInsuranceFeeId(insuranceFeeId: Long): Settlement
    fun findSettlementDetailsBySettlementId(settlementId: Long): List<SettlementDetail>
    fun updateToCompleted(settlement: Settlement)
}

class SettlementRepositoryImpl : SettlementRepository {
    override fun findSettlementById(settlementId: Long): Settlement {
        return transaction {
            Settlement.findById(settlementId) ?: throw NoSuchElementException("정산 요청 항목이 없습니다.")
        }
    }

    override fun findSettlementDetailById(settlementDetailId: Long): SettlementDetail {
        return transaction {
            SettlementDetail.findById(settlementDetailId)
                ?: throw NoSuchElementException("요청받은 정산 항목이 없습니다.")
        }
    }

    override fun findSettlementDetails(requestedPersonId: Long): List<SettlementDetail> {
        return transaction {
            SettlementDetail.find {
                (SettlementDetails.requestedPersonId eq requestedPersonId) and
                        (SettlementDetails.completed eq false)
            }.toList()
        }

    }

    override fun remitSettlements(settlementDetails: List<SettlementDetail>) {
        transaction {
            settlementDetails.map { detail ->
                detail.completed = true
                detail.completionDateTime = LocalDateTimeUtils.now()
            }
        }
    }

    override fun remitSettlement(settlementDetail: SettlementDetail) {
        transaction {
            settlementDetail.completed = true
            settlementDetail.completionDateTime = LocalDateTimeUtils.now()
        }
    }

    override fun createSettlement(settlementDto: SettlementCreateDto): Settlement {
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
