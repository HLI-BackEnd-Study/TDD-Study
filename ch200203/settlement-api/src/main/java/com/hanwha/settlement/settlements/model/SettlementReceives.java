package com.hanwha.settlement.settlements.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementReceives {
    @OneToMany(mappedBy = "settlement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SettlementReceive> settlementReceives;

    public SettlementReceives(List<SettlementReceive> settlementReceives) {
        validateSettlement(settlementReceives);
        this.settlementReceives = settlementReceives;
    }

    public int calculateTotalAmount() {
        return settlementReceives.stream().mapToInt(SettlementReceive::getAmount).sum();
    }

    public List<SettlementReceive> getSettlementReceives() {
        return Collections.unmodifiableList(settlementReceives);
    }

    private void validateSettlement(List<SettlementReceive> settlementReceives) {

        if (settlementReceives.isEmpty()) {
            throw new IllegalArgumentException("참가자가 최소 한 명 이상이어야 합니다");
        }

        for (SettlementReceive receive : settlementReceives) {
            if (receive.getAmount() <= 0) {
                throw new IllegalArgumentException("모든 참가자에 대해 금액은 0보다 커야 합니다");
            }
        }
    }
}
