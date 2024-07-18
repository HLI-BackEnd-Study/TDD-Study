package com.hanwha.settlement.settlements.mapper;

import com.hanwha.settlement.settlements.dto.SettlementReceivesResponse;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import org.springframework.stereotype.Component;

@Component
public class SettlementMapper {

    public SettlementReceivesResponse settlementReceiveToResponse(SettlementReceive settlementReceive) {
        return SettlementReceivesResponse.builder()
                .id(settlementReceive.getId())
                .settlementId(settlementReceive.getSettlement().getId())
                .userName(settlementReceive.getUser().getName())
                .amount(settlementReceive.getAmount())
                .status(settlementReceive.isStatus())
                .build();
    }
}
