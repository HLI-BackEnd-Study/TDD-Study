package com.hanwha.settlement.settlements.model;

import com.hanwha.settlement.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementReceive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int amount; // 정산해야할 금액

    private boolean status; // 정산 완료 상태

    private SettlementReceive(Settlement settlement, User user, int amount) {
        validateAmount(amount);
        this.settlement = settlement;
        this.user = user;
        this.amount = amount;
        this.status = false;
    }

    public static SettlementReceive create(Settlement settlement, User user, int amount) {
        return new SettlementReceive(settlement, user, amount);
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("정산금액은 0원 보다 커야합니다.");
        }
    }

    // 요청한 정산금액을 정산한다.
    public void paid(int requestAmount) {
        validatePayAmount(requestAmount);
        this.status = true; // 정산완료로 변경
    }

    private void validatePayAmount(int requestAmount) {
        if (this.amount == requestAmount) {
            throw new IllegalArgumentException("정산할 금액이 일치 하지 않습니다.");
        }
    }
}
