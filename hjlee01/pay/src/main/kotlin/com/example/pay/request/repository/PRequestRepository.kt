package com.example.pay.request.repository

import com.example.pay.entity.PRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PRequestRepository: JpaRepository<PRequest, String> {
}