package com.example.pay.request.service

import com.example.pay.entity.PDetail
import com.example.pay.entity.PRequest
import com.example.pay.enum.RequestStatus
import com.example.pay.request.dto.PDetailDto
import com.example.pay.request.repository.PDetailRepository
import com.example.pay.request.repository.PRequestRepository
import org.springframework.stereotype.Service

@Service
class PRequestService(
        private val pRequestRepository: PRequestRepository,
        private val pDetailRepository: PDetailRepository
) {
    /**
     * 지불 요청
     */
    fun request(pDetailList: List<PDetailDto>) {
        // 지불 기본 정보 저장
        val pRequest = PRequest(
                seq = null,
                amount = pDetailList.get(0).totalAmount,
                status = RequestStatus.요청.code
        )
        val request = pRequestRepository.save(pRequest)

        // 지불 상세 저장
        pDetailList.forEach { it ->
            val pDetail = request.seq?.let { pNo ->
                PDetail(
                        payNo = pNo,
                        id = it.id,
                        amount = it.amount,
                        request = request
                )
            }
            pDetailRepository.save(pDetail)
        }
    }
}