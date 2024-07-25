package com.hanwha.settlement.settlements.mapper;

import com.hanwha.settlement.settlements.dto.SettlementReceivesResponse;
import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import com.hanwha.settlement.users.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.mock;

class SettlementMapperTest {

    SettlementMapper settlementMapper = new SettlementMapper();

    @Test
    void 엔티티를_반환_DTO로_변환하여_반환한다() {
        //Given
        Settlement settlement = mock(Settlement.class);
        User user = new User(1L, "메시");
        SettlementReceive settlementReceive = SettlementReceive.create(settlement, user, 10_000);

        // When
        SettlementReceivesResponse result = settlementMapper.settlementReceiveToResponse(settlementReceive);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(result.userName()).isEqualTo("메시");
            softly.assertThat(result.settlementId()).isEqualTo(settlement.getId());
            softly.assertThat(result.amount()).isEqualTo(10_000);
            softly.assertThat(result.status()).isFalse();
        });
    }

}
