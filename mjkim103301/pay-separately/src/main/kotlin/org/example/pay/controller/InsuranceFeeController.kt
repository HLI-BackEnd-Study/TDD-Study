package org.example.pay.controller

import org.example.pay.dto.InsuranceFeeDto
import org.example.pay.service.InsuranceFeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/vi/insurance-fee")
class InsuranceFeeController(private val insuranceFeeService: InsuranceFeeService) {
    /**
     * 나의 보험료 조회
     */
    @GetMapping("/{user-id}")
    fun findInsuranceFeesById(@PathVariable userId: Long): ResponseEntity<List<InsuranceFeeDto>> {
        val insuranceFees = insuranceFeeService.findInsuranceFee(userId)
        return ResponseEntity.ok(insuranceFees)
    }

    @PutMapping("/{id}")
    fun checkInsuranceFeePaymentIsFinished(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isFinished = insuranceFeeService.checkInsuranceFeePaymentIsFinished(id)
        return ResponseEntity.ok(isFinished)
    }
}