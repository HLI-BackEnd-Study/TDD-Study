package com.hanwha.settlement.settlements;

import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import com.hanwha.settlement.settlements.model.SettlementReceives;
import com.hanwha.settlement.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


class SettlementTest {

    private User requester;
    private User participant1;
    private User participant2;

    @BeforeEach
    void setUp() {
        requester = new User(1L, "requester");
        participant1 = new User(2L, "participant1");
        participant2 = new User(3L, "participant2");
    }

    @Test
    void 정산_객체를_생성한다() {
        // given
        User requester = new User(1L, "requester");
        int totalAmount = 100;

        assertThatCode(() -> Settlement.create(requester, totalAmount))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("모든 합계 금액이 총 금액과 일치하지 않는 다면 예외를 반환한다.")
    void createSettlementInvalidTotalAmount() {
        Settlement settlement = Settlement.create(requester, 100);
        List<SettlementReceive> invalidSettlementReceives = new ArrayList<>();
        invalidSettlementReceives.add(SettlementReceive.create(settlement, participant1, 150));
        invalidSettlementReceives.add(SettlementReceive.create(settlement, participant2, 50));

        SettlementReceives settlementReceives = SettlementReceives.of(invalidSettlementReceives);


        assertThatThrownBy(() -> settlement.addSettlementReceives(settlementReceives))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정산 요청한 금액과 참가자들의 금액 합계가 일치하지 않습니다");
    }

}
