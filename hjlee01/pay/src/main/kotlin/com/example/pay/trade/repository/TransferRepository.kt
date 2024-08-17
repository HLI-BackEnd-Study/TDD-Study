package com.example.pay.trade.repository

import com.example.pay.entity.PRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransferRepository: JpaRepository<PRequest, String> {
}