package com.example.pay.trade.service

import com.example.pay.trade.repository.TransferRepository
import org.springframework.stereotype.Service

@Service
class TransferService(
        private val transferRepository: TransferRepository
) {
}