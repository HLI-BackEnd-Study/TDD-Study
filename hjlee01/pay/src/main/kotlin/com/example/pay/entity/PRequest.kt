package com.example.pay.entity

import jakarta.persistence.*
import org.hibernate.internal.util.collections.CollectionHelper.listOf

/**
 * 지불 요청
 */
@Entity
@Table(name = "request")
class PRequest(
        /**
         * 지불 순번
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val seq: Long?,
        /**
         * 지불 총 금액
         */
        val amount: Long,
        /**
         * 지불 상태
         */
        val status: String,
        /**
         * 지불 상세
         */
        @OneToMany(mappedBy = "payRequest")
        val details: MutableList<PDetail> = listOf()
)