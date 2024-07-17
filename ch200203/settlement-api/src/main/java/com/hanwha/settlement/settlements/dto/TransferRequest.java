package com.hanwha.settlement.settlements.dto;

public record TransferRequest(
        Long userId,
        int amount
) {
}
