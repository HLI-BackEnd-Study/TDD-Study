package com.example.pay.request.dto

import com.example.pay.entity.PRequest
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

/**
 * 지불 상세 DTO
 */
data class PDetailDto(
        /**
         * 아이디
         */
        val id: String,
        /**
         * 지불 번호
         */
        val payNo: Long,
        /**
         * 지불 총 금액
         */
        val totalAmount: Long,
        /**
         * 금액
         */
        val amount: Long,
        /**
         * 지불 요청 정보
         */
        val request: PRequestDto
)
