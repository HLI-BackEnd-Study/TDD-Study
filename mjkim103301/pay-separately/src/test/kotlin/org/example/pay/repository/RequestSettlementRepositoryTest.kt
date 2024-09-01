package org.example.pay.repository

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.example.pay.DatabaseConnectTest
import org.example.pay.domain.model.User
import org.example.pay.dto.request.SettlementCreateDto
import org.example.pay.dto.request.SettlementDetailCreateDto
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

/**
 * 정산금 요청 레포지토리 테스트
 */
class RequestSettlementRepositoryTest : DatabaseConnectTest() {
    private val repository: RequestSettlementRepository = RequestSettlementRepositoryImpl()

    @BeforeEach
    fun setUp() {
        // 사용자 세팅
        transaction {
            // 정산 요청자
            User.new {
                name = "홍길동"
            }

            // 요청받은 사람들
            User.new {
                name = "심청이1"
            }
            User.new {
                name = "심청이2"
            }
        }
    }

    @Test
    fun `정산금 요청정보 저장 테스트`() {
        // given
        val requestNameValue: String = "한화생명 보험료 정산"
        val requesterIdValue: Long = 1
        val settlementDto = SettlementCreateDto(
            requestName = requestNameValue,
            requesterId = requesterIdValue,
            insuranceFeeId = 1,
            amount = BigDecimal(30_000),
            discountAmount = BigDecimal.ZERO,
            requestDetails = listOf(
                SettlementDetailCreateDto(
                    amount = BigDecimal(10_000),
                    requestedPersonId = 1
                ),
                SettlementDetailCreateDto(
                    amount = BigDecimal(10_000),
                    requestedPersonId = 2
                ),
                SettlementDetailCreateDto(
                    amount = BigDecimal(10_000),
                    requestedPersonId = 3
                )
            )
        )
        val discountAmount = BigDecimal.ZERO

        repository.createSettlement(settlementDto)


        // when
        val results = repository.findSettlements(requesterIdValue)

        // then
        assertSoftly {
            results.forEach {
                assertThat(it.requestName).isEqualTo(requestNameValue)
                assertThat(it.requesterId).isEqualTo(requesterIdValue)
            }
        }
    }
}
