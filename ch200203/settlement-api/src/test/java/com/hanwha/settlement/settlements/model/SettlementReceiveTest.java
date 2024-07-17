package com.hanwha.settlement.settlements.model;

import com.hanwha.settlement.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class SettlementReceiveTest {

    private User requester;
    private User participant;
    private Settlement settlement;

    @BeforeEach
    void setUp() {
        requester = new User(1L, "requester");
        participant = new User(2L, "participant");

        int totalAmount = 50_000;

        settlement = Settlement.create(requester, totalAmount);
    }

    @Test
    void 정산할_객체를_생성한다() {
        // Given
        int amount = 50_000;

        // When
        SettlementReceive settlementReceive = SettlementReceive.create(settlement, participant, amount);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(settlementReceive).isNotNull();
            softly.assertThat(settlementReceive.getSettlement()).isEqualTo(settlement);
            softly.assertThat(settlementReceive.getUser()).isEqualTo(participant);
            softly.assertThat(settlementReceive.getAmount()).isEqualTo(amount);
            softly.assertThat(settlementReceive.isStatus()).isFalse();
        });
    }

    @Test
    void 정산금액이_0보다_작거나_같으면_예외를_반환한다() {
        // Given
        int invalidAmount = 0;

        // When & Then
        assertThatThrownBy(() -> SettlementReceive.create(settlement, participant, invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정산금액은 0원 보다 커야합니다.");
    }

    @Test
    void 정산_완료_후_정산완료_상태로_변경한다() {
        // Given
        int amount = 50_000;
        SettlementReceive settlementReceive = SettlementReceive.create(settlement, participant, amount);

        // When
        settlementReceive.paid(amount);

        // Then
        assertThat(settlementReceive.isStatus()).isTrue();
    }

    @Test
    void 정산_금액이_일치하지_않는_경우_예외를_반환한다() {
        // Given
        int amount = 50_000;
        SettlementReceive settlementReceive = SettlementReceive.create(settlement, participant, amount);

        // When
        int requestAmount = 49_000;

        // Then
        assertSoftly(softtly -> {
            softtly.assertThatThrownBy(() -> settlementReceive.paid(requestAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("정산할 금액이 일치 하지 않습니다.");

            softtly.assertThat(settlementReceive.isPaid()).isFalse();
        });


    }

}
