package com.hanwha.settlement.settlements;

import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import com.hanwha.settlement.users.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class SettlementTest {

    @Test
    @DisplayName("정산 객체를 생성한다")
    void create() {
        User requester = new User(1L, "requester");
        User participant1 = new User(2L, "participant1");
        User participant2 = new User(3L, "participant2");

        List<SettlementReceive> settlementReceives = new ArrayList<>();
        settlementReceives.add(SettlementReceive.create(null, participant1, 50));
        settlementReceives.add(SettlementReceive.create(null, participant2, 50));
        int totalAmount = 100;

        Settlement settlement = Settlement.create(requester, settlementReceives, totalAmount);

        assertThatCode(() -> Settlement.create(requester, settlementReceives, totalAmount))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("정산 객체 생성 시 모든 참가자의 금액은 0보다 크지 않다면 예외를 반환한다.")
    void createSettlementInvalidAmount() {
        User requester = new User(1L, "requester");
        User participant1 = new User(2L, "participant1");
        User participant2 = new User(3L, "participant2");

        List<SettlementReceive> invalidSettlementReceives = new ArrayList<>();
        invalidSettlementReceives.add(SettlementReceive.create(null, participant1, 0));
        invalidSettlementReceives.add(SettlementReceive.create(null, participant2, 50));

        int totalAmount = 50;

        assertThatThrownBy(() -> Settlement.create(requester, invalidSettlementReceives, totalAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("모든 참가자에 대해 금액은 0보다 커야 합니다");
    }

    @Test
    @DisplayName("정산 객체 생성 시 모든 합계 금액이 총 금액과 일치하지 않는 다면 예외를 반환한다.")
    void createSettlementInvalidTotalAmount() {
        User requester = new User(1L, "requester");
        User participant1 = new User(2L, "participant1");
        User participant2 = new User(3L, "participant2");

        List<SettlementReceive> invalidSettlementReceives = new ArrayList<>();
        invalidSettlementReceives.add(SettlementReceive.create(null, participant1, 150));
        invalidSettlementReceives.add(SettlementReceive.create(null, participant2, 50));

        int totalAmount = 150;

        assertThatThrownBy(() -> Settlement.create(requester, invalidSettlementReceives, totalAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정산 요청한 금액과 참가자들의 금액 합계가 일치하지 않습니다");
    }

    @Test
    @DisplayName("정산 받을 유저가 없는 경우 예외를 반환합니다.")
    void createSettlementHasNoParticipants() {
        User requester = new User(1L, "requester");


        List<SettlementReceive> invalidSettlementReceives = new ArrayList<>();

        int totalAmount = 150;

        assertThatThrownBy(() -> Settlement.create(requester, invalidSettlementReceives, totalAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자가 최소 한 명 이상이어야 합니다");
    }
}
