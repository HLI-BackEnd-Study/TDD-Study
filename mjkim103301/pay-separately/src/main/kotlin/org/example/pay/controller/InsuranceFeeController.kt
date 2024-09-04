package org.example.pay.controller

import org.example.pay.dto.InsuranceFeeDto
import org.example.pay.service.InsuranceFeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/vi/insurance-fee")
class InsuranceFeeController @Autowired constructor(private val insuranceFeeService: InsuranceFeeService) {
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
