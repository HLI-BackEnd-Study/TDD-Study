package com.hanwha.settlement.settlements.model;

import com.hanwha.settlement.users.User;
import jakarta.persistence.*;

import java.util.List;

public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_user_id")
    private User requestUser; // 정산을 요청한 유저

    private int totalAmount; // 총 정산 금액

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus; // 요청 상태(PENDING, COMPLETED)
    @Embedded
    private SettlementReceives settlementReceives;

    private Settlement(User requestUser, SettlementReceives settlementReceives, int totalAmount) {
        this.requestUser = requestUser;
        this.settlementReceives = settlementReceives;
        this.totalAmount = totalAmount;
        this.requestStatus = RequestStatus.PENDING;
        validateSettlement(totalAmount);
    }

    public static Settlement create(User requestUser, List<SettlementReceive> settlementReceiveList, int totalAmount) {
        SettlementReceives settlementReceives = new SettlementReceives(settlementReceiveList);
        return new Settlement(requestUser, settlementReceives, totalAmount);
    }

    private void validateSettlement(int totalAmount) {
        int calculatedTotalAmount = settlementReceives.calculateTotalAmount();

        if (calculatedTotalAmount != totalAmount) {
            throw new IllegalArgumentException("정산 요청한 금액과 참가자들의 금액 합계가 일치하지 않습니다");
        }
    }
}
