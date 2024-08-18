package org.example.pay.repository

import org.example.pay.domain.model.InsuranceFee
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CalculateInsuranceFeeRepository : JpaRepository<InsuranceFee, Long> {
    fun findByUserIdAndPaymentCompletedIsFalse(id: Long): Optional<InsuranceFee>
}
