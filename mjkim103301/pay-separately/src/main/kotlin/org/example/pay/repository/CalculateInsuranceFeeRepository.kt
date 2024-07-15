package org.example.pay.repository

import org.example.pay.model.InsuranceFee
import org.springframework.data.jpa.repository.JpaRepository

interface CalculateInsuranceFeeRepository : JpaRepository<InsuranceFee, Long> {

}
