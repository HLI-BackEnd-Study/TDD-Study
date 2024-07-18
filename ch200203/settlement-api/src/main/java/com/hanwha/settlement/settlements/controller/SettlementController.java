package com.hanwha.settlement.settlements.controller;

import com.hanwha.settlement.settlements.dto.CreateRequest;
import com.hanwha.settlement.settlements.dto.SettlementReceivesResponse;
import com.hanwha.settlement.settlements.dto.TransferRequest;
import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/settlements")
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;

    @PostMapping
    public ResponseEntity<Settlement> requestSettlement(@RequestBody CreateRequest request) {
        Settlement settlement = settlementService.request(request);
        return ResponseEntity.ok(settlement);
    }

    /**
     * 전달 받은 금액을 정산한다.
     */
    @PostMapping("/{settlementReceiveId}/transfer")
    public ResponseEntity<Void> transferPay(@PathVariable Long settlementReceiveId, @RequestBody TransferRequest request) {
        settlementService.transferPay(settlementReceiveId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/settlementReceives/{userId}")
    public ResponseEntity<List<SettlementReceivesResponse>> getSettlementReceives(@PathVariable Long userId) {
        List<SettlementReceivesResponse> settlementReceivesResponses = settlementService.getSettlementReceives(userId);
        return ResponseEntity.ok().body(settlementReceivesResponses);
    }

}
