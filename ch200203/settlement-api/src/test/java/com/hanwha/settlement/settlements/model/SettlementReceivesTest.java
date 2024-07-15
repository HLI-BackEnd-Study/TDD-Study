package com.hanwha.settlement.settlements.model;

import com.hanwha.settlement.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class SettlementReceivesTest {

    private User requester;
    private User participant1;
    private User participant2;
    private Settlement settlement;

    @BeforeEach
    void setUp() {
        requester = new User(1L, "requester");
        participant1 = new User(2L, "participant1");
        participant2 = new User(3L, "participant2");

        int totalAmount = 50_000;

        settlement = Settlement.create(requester, totalAmount);
    }

    @Test
    void 정산받을_명단으로_부터_리스트_객체를_생성한다() {
        // given
        List<SettlementReceive> settlementReceiveList = new ArrayList<>();
        settlementReceiveList.add(SettlementReceive.create(settlement, participant1, 25_000));
        settlementReceiveList.add(SettlementReceive.create(settlement, participant2, 25_000));

        // when & then
        assertThat(SettlementReceives.of(settlementReceiveList).getSettlementReceives())
                .isEqualTo(settlementReceiveList);
    }

    @Test
    void 참가자가_최소_한_명_이상이어야_한다() {
        // given
        List<SettlementReceive> emptySettlementReceives = new ArrayList<>();

        // when & then
        assertThatThrownBy(() -> SettlementReceives.of(emptySettlementReceives))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자가 최소 한 명 이상이어야 합니다");
    }

    @Test
    void 정산하는_금액이_0보다_커야_한다() {
        // given
        List<SettlementReceive> settlementReceiveList = new ArrayList<>();
        SettlementReceive settlementReceive = SettlementReceive.create(settlement, participant1, 25_000);
        SettlementReceive invalid = SettlementReceive.create(settlement, participant2, 0);

        settlementReceiveList.add(settlementReceive);
        settlementReceiveList.add(invalid);

        // when & then
        assertThatThrownBy(() -> SettlementReceives.of(settlementReceiveList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("모든 참가자에 대해 금액은 0보다 커야 합니다");
    }

}
