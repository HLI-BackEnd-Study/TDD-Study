package org.example.pay.repository

import org.assertj.core.api.Assertions.assertThat
import org.example.pay.DatabaseConnectTest
import org.example.pay.domain.model.InsuranceFee
import org.example.pay.domain.model.User
import org.example.pay.dto.SettlementDetailDto
import org.example.pay.dto.SettlementDto
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

/**
 * 정산금 송금 레포지토리 테스트
 */
class RemitSettlementRepositoryTest() : DatabaseConnectTest() {

    private var remitSettlementRepository: RemitSettlementRepository = RemitSettlementRepositoryImpl()
    private var requestSettlementRepository: RequestSettlementRepository = RequestSettlementRepositoryImpl()

    @DisplayName("사용자 정보, 정산금 요청 정보 저장")
    @BeforeEach
    fun setUp() {
        val requestNameValue: String = "한화생명 보험료 정산"
        val requesterIdValue: Long = 1
        val settlementDto = SettlementDto(
            requestName = requestNameValue,
            requesterId = requesterIdValue,
            insuranceFeeId = 1,
            amount = BigDecimal(30_000),
            discountAmount = BigDecimal.ZERO,
            requestDetails = listOf(
                SettlementDetailDto(
                    amount = BigDecimal(10_000),
                    requestedPersonId = 1
                ),
                SettlementDetailDto(
                    amount = BigDecimal(10_000),
                    requestedPersonId = 2
                ),
                SettlementDetailDto(
                    amount = BigDecimal(10_000),
                    requestedPersonId = 3
                )
            )
        )
        transaction {
            // 정산 요청자
            User.new {
                name = "홍길동"
            }

            // 나머지 사람들
            User.new {
                name = "심청이1"
            }
            User.new {
                name = "심청이2"
            }

            InsuranceFee.new {
                userId = 1
                premium = settlementDto.amount
            }
        }
        requestSettlementRepository.createSettlement(settlementDto)
    }

    @Test
    fun `요청받은 정산금 송금 테스트`() {
        val requestedUserId: Long = 2
        val requestedSettlements = remitSettlementRepository.findSettlementDetails(requestedUserId)

        remitSettlementRepository.remitSettlements(requestedSettlements)

        val results = remitSettlementRepository.findSettlementDetails(requestedUserId)

        assertThat(results.size).isEqualTo(0)
    }
}
