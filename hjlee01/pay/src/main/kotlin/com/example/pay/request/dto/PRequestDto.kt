package com.example.pay.request.dto

import com.example.pay.entity.PDetail
import org.hibernate.internal.util.collections.CollectionHelper

/**
 * 지불 요청 DTO
 */
data class PRequestDto(
        /**
         * 지불 순번
         */
        val seq: Long,
        /**
         * 지불 총 금액
         */
        val amount: Long,
        /**
         * 잔액
         */
        val remain: Long,
        /**
         * 지불 상태
         */
        val status: String,
        /**
         * 지불 상세 내역
         */
        val details: MutableList<PDetailDto>
)
