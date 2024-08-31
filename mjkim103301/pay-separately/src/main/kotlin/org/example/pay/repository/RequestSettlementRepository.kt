package org.example.pay.repository

import org.example.pay.domain.model.Settlement
import org.example.pay.domain.model.SettlementDetail
import org.example.pay.domain.table.Settlements
import org.example.pay.dto.SettlementDto
import org.jetbrains.exposed.sql.transactions.transaction
import java.math.BigDecimal


interface RequestSettlementRepository {
    fun createSettlement(settlementDto: SettlementDto, discountAmountValue: BigDecimal): Settlement
    fun findSettlements(requesterId: Long): List<Settlement>


}

class RequestSettlementRepositoryImpl : RequestSettlementRepository {
    override fun createSettlement(settlementDto: SettlementDto, discountAmountValue: BigDecimal): Settlement {
        return transaction {
            val settlement = Settlement.new {
                requestName = settlementDto.requestName
                requesterId = settlementDto.requesterId
                amount = settlementDto.amount
                discountAmount = discountAmountValue
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


}
