package com.hanwha.settlement.settlements.model;

import com.hanwha.settlement.users.User;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private Settlement(User requestUser, int totalAmount) {
        this.requestUser = requestUser;
        this.totalAmount = totalAmount;
        this.requestStatus = RequestStatus.PENDING;
    }

    public static Settlement create(User requestUser, int totalAmount) {
        return new Settlement(requestUser, totalAmount);
    }

    public void addSettlementReceives(SettlementReceives settlementReceives) {
        validateSettlement(settlementReceives);
        this.settlementReceives = settlementReceives;
    }

    private void validateSettlement(SettlementReceives settlementReceives) {
        int calculatedTotalAmount = settlementReceives.calculateTotalAmount();
        if (calculatedTotalAmount != totalAmount) {
            throw new IllegalArgumentException("정산 요청한 금액과 참가자들의 금액 합계가 일치하지 않습니다");
        }
    }
}
