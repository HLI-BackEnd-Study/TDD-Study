package org.example.pay.dto

import kotlinx.datetime.LocalDateTime
import java.math.BigDecimal

/**
 * 내가 정산해야 할 항목 DTO
 *
 * @property settlementId 정산 요청 ID
 * @property requestName 정산 이름
 * @property requesterId 정산 요청자 ID
 * @property requesterName 정산 요청자 이름
 * @property settlementDetailId 정산 요청 상세 ID
 * @property amount 내가 정산해야 할 금액
 * @property completed 정산 완료 여부
 * @property completionDateTime 정산 완료 시간
 */

data class SettlementDetailResponseDto(
    val settlementId: Long,
    val requestName: String,
    val requesterId: Long,
    val requesterName: String,
    val settlementDetailId: Long,
    val amount: BigDecimal,
    val completed: Boolean = false,
    val completionDateTime: LocalDateTime? = null,
)
