package com.example.pay.trade.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "trade")
class Transfer(
        /**
         *
         */
        val id: String,
        val ymd: String,
        val seq: Integer,
        val status: String,
        val amount: Long,
        val remain: Long
) {
}