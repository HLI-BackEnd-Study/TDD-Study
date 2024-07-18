package com.hanwha.settlement.settlements.dto;

import lombok.Builder;

@Builder
public record SettlementReceivesResponse(
        Long id,
        Long settlementId,
        String userName,
        int amount,
        boolean status
) {

}
