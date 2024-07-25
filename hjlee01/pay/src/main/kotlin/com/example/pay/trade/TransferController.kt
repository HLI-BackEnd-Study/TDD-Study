package com.example.pay.trade

import com.example.pay.trade.service.TransferService
import org.springframework.web.bind.annotation.RestController

@RestController
class TransferController(
        private val transferService: TransferService
) {
}