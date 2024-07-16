package com.hanwha.settlement.settlements.controller;

import com.hanwha.settlement.settlements.dto.CreateRequest;
import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/complete/{settlementReceiveId}")
    public ResponseEntity<Void> completePayment(@PathVariable Long settlementReceiveId) {
        settlementService.completePayment(settlementReceiveId);
        return ResponseEntity.ok().build();
    }

}
