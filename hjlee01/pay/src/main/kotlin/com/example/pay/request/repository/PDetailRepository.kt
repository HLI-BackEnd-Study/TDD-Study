package com.example.pay.request.repository

import com.example.pay.entity.PDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PDetailRepository: JpaRepository<PDetail, String> {
}