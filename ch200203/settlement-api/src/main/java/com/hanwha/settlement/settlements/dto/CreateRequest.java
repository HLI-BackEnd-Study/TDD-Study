package com.hanwha.settlement.settlements.dto;

import java.util.List;

public record CreateRequest(

        long userId,
        int totalAmount,
        List<RequestReceive> requestReceives
) {

    public record RequestReceive(
            long userId,
            int amount
    ) {
    }

}
