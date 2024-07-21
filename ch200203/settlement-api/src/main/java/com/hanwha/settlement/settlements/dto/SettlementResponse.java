package com.hanwha.settlement.settlements.dto;

import com.hanwha.settlement.settlements.model.RequestStatus;
import com.hanwha.settlement.users.User;
import lombok.Builder;

@Builder
public record SettlementResponse(
        Long id,
        User requestUser,
        int totalAmount,
        RequestStatus requestStatus
) {
}
