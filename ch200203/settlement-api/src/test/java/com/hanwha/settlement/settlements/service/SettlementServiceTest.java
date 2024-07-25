package com.hanwha.settlement.settlements.service;

import com.hanwha.settlement.settlements.dto.CreateRequest;
import com.hanwha.settlement.settlements.mapper.SettlementMapper;
import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import com.hanwha.settlement.settlements.repository.SettlementReceiveRepository;
import com.hanwha.settlement.settlements.repository.SettlementRepository;
import com.hanwha.settlement.users.User;
import com.hanwha.settlement.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SettlementServiceTest {

    @InjectMocks
    private SettlementService settlementService;

    @Mock
    SettlementMapper mapper;

    @Mock
    private SettlementRepository settlementRepository;

    @Mock
    private SettlementReceiveRepository settlementReceiveRepository;

    @Mock
    private UserService userService;

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
    void 정산_엔티티를_생성한다() {
        // Given
        CreateRequest.RequestReceive receive1 = new CreateRequest.RequestReceive(2L, 50);
        CreateRequest.RequestReceive receive2 = new CreateRequest.RequestReceive(3L, 50);
        List<CreateRequest.RequestReceive> requestReceives = Arrays.asList(receive1, receive2);

        CreateRequest createRequest = new CreateRequest(1L, 100, requestReceives);

        when(userService.getUserById(1L)).thenReturn(requester);
        when(userService.getUserById(2L)).thenReturn(participant1);
        when(userService.getUserById(3L)).thenReturn(participant2);
        when(settlementRepository.save(any(Settlement.class))).thenAnswer(it -> it.getArgument(0));

        // When
        Settlement result = settlementService.request(createRequest);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(result.getRequestUser()).isEqualTo(requester);
            softly.assertThat(result.getTotalAmount()).isEqualTo(100);
            softly.assertThat(result.getSettlementReceives().getSettlementReceives()).hasSize(2);
            softly.assertThat(result.getSettlementReceives().getSettlementReceives().get(0).getUser()).isEqualTo(participant1);
            softly.assertThat(result.getSettlementReceives().getSettlementReceives().get(1).getUser()).isEqualTo(participant2);
            softly.assertThat(result).isNotNull();
        });
    }

    @Test
    void 개별_정산금액이_0인_경우_예외를_반환한다() {
        // Given
        User requestUser = new User(1L, "Request User");
        CreateRequest.RequestReceive receive = new CreateRequest.RequestReceive(2L, 0);
        List<CreateRequest.RequestReceive> requestReceives = Collections.singletonList(receive);
        CreateRequest createRequest = new CreateRequest(1L, 100, requestReceives);

        when(userService.getUserById(1L)).thenReturn(requestUser);
        when(userService.getUserById(2L)).thenReturn(new User(2L, "Receive User"));

        // When & Then
        assertThatThrownBy(() -> settlementService.request(createRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정산금액은 0원 보다 커야합니다.");
    }

    @Test
    void 총_정산금액과_개별정산_금액의_합계가_일치하지_않는_경우_예외를_반환한다() {
        // Given
        User requestUser = new User(1L, "Request User");
        CreateRequest.RequestReceive receive1 = new CreateRequest.RequestReceive(2L, 50);
        CreateRequest.RequestReceive receive2 = new CreateRequest.RequestReceive(3L, 30);
        List<CreateRequest.RequestReceive> requestReceives = Arrays.asList(receive1, receive2);
        CreateRequest createRequest = new CreateRequest(1L, 100, requestReceives);

        when(userService.getUserById(1L)).thenReturn(requestUser);
        when(userService.getUserById(2L)).thenReturn(new User(2L, "Receive User 1"));
        when(userService.getUserById(3L)).thenReturn(new User(3L, "Receive User 2"));

        // When & Then
        assertThatThrownBy(() -> settlementService.request(createRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정산 요청한 금액과 참가자들의 금액 합계가 일치하지 않습니다");
    }

    @Test
    void 요청한_유저가_존재하지_않는_경우_예외를_던진다() {
        // Given
        CreateRequest.RequestReceive receive1 = new CreateRequest.RequestReceive(2L, 50);
        CreateRequest.RequestReceive receive2 = new CreateRequest.RequestReceive(3L, 50);
        List<CreateRequest.RequestReceive> requestReceives = Arrays.asList(receive1, receive2);
        CreateRequest createRequest = new CreateRequest(1L, 100, requestReceives);

        when(userService.getUserById(1L)).thenThrow(new IllegalArgumentException("존재하지 않는 유저 입니다 : 1"));

        // When & Then
        assertThatThrownBy(() -> settlementService.request(createRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 유저 입니다 : 1");
    }

    @Test
    void 참가자가_존재하지_않는_경우_예외를_던진다() {
        // Given
        User requestUser = new User(1L, "Request User");
        List<CreateRequest.RequestReceive> requestReceives = Collections.emptyList();
        CreateRequest createRequest = new CreateRequest(1L, 100, requestReceives);

        when(userService.getUserById(1L)).thenReturn(requestUser);

        // When & Then
        assertThatThrownBy(() -> settlementService.request(createRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자가 최소 한 명 이상이어야 합니다");

    }

    @Test
    void 요청받은_사람은_자신이_요청받은_정산하기_전체_리스트를_확인할_수_있다() { // 행위를 기반으로 테스트 메서드 명을 작성
        // Given
        Settlement settlement1 = Settlement.create(requester, 50_000);
        Settlement settlement2 = Settlement.create(requester, 45_000);
        SettlementReceive settlementReceive1 = SettlementReceive.create(settlement1, participant1, 50_0000);
        SettlementReceive settlementReceive2 = SettlementReceive.create(settlement2, participant1, 45_0000);

        // When
        when(settlementReceiveRepository.findSettlementReceivesByUserId(participant1.getId())).thenReturn(List.of(settlementReceive1, settlementReceive2));

        // Then
        assertSoftly(softly -> {
            softly.assertThat(settlementService.getSettlementReceives(participant1.getId())).isNotNull();
            softly.assertThat(settlementService.getSettlementReceives(participant1.getId()))
                    .contains(mapper.settlementReceiveToResponse(settlementReceive1), mapper.settlementReceiveToResponse(settlementReceive2));
            softly.assertThat(settlementService.getSettlementReceives(participant1.getId())).hasSize(2);
        });
    }


}


