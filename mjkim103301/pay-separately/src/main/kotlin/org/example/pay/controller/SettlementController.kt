package org.example.pay.controller

import org.example.pay.dto.SettlementDto
import org.example.pay.dto.request.SettlementCreateDto
import org.example.pay.dto.request.SettlementUpdateDto
import org.example.pay.service.CalculateSettlementService
import org.example.pay.service.RemitSettlementService
import org.example.pay.service.RequestSettlementSerivce
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/settlements")
class SettlementController(

    private val remitSettlementService: RemitSettlementService,
    private val requestSettlementService: RequestSettlementSerivce,
    private val calculateSettlementService: CalculateSettlementService
) {


    /**
     * 보험료 정산 요청
     */
    @PostMapping("/request")
    fun createSettlement(@RequestBody settlementCreateDto: SettlementCreateDto): ResponseEntity<SettlementDto> {
        val settlementDto = requestSettlementService.createRequestedSettlements(settlementCreateDto)
        return ResponseEntity.ok(settlementDto)
    }

    /**
     * 정산금 송금
     */
    @PostMapping("/remit")
    fun remitSettlement(@RequestBody settlementUpdateDto: SettlementUpdateDto): ResponseEntity<Unit> {
        remitSettlementService.remitSettlement(settlementUpdateDto.settlementDetailId)
        return ResponseEntity.ok().build()
    }


    /**
     * 정산 요청한 항목 납입 완료 확인
     */
    @PutMapping("/{id}")
    fun checkSettlementIsCompleted(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isCompleted = requestSettlementService.checkSettlementIsCompleted(id)
        return ResponseEntity.ok(isCompleted)
    }

}
