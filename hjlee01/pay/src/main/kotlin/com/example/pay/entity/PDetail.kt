package com.example.pay.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

/**
 * 지불 상세
 */
@Entity
@Table(name = "datail")
class PDetail(
        /**
         * 아이디
         */
        @Id
        val id: String,
        /**
         * 지불 번호
         */
        val payNo: Long,
        /**
         * 금액
         */
        val amount: Long,
        @ManyToOne
        @JoinColumn(name = "pay_id")
        val request: PRequest
)