package com.example.pay.request

import com.example.pay.request.dto.PDetailDto
import com.example.pay.request.service.PRequestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/request")
class PRequestController(
        private val pRequestService: PRequestService
) {
    fun request(detailList: List<PDetailDto>): ResponseEntity<String> {
        pRequestService.request(detailList)
        return ResponseEntity.ok("지불 요청 성공")
    }
}