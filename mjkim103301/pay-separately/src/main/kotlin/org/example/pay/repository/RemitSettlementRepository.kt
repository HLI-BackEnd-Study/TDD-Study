package org.example.pay.repository

import org.example.pay.domain.model.Settlement
import org.example.pay.domain.model.SettlementDetail
import org.example.pay.domain.table.SettlementDetails
import org.example.pay.util.LocalDateTimeUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
interface RemitSettlementRepository {
    fun findSettlementById(settlementId: Long): Settlement
    fun findSettlementDetailById(settlementDetailId: Long): SettlementDetail
    fun findSettlementDetails(requestedPersonId: Long): List<SettlementDetail>
    fun remitSettlements(settlementDetails: List<SettlementDetail>)

    fun remitSettlement(settlementDetail: SettlementDetail)
}

class RemitSettlementRepositoryImpl : RemitSettlementRepository {
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
}
