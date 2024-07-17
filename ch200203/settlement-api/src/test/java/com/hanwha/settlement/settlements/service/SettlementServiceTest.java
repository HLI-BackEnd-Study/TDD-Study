package com.hanwha.settlement.settlements.service;

import com.hanwha.settlement.settlements.dto.CreateRequest;
import com.hanwha.settlement.settlements.model.Settlement;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SettlementServiceTest {

    @InjectMocks
    private SettlementService settlementService;

    @Mock
    private SettlementRepository settlementRepository;

    @Mock
    private SettlementReceiveRepository settlementReceiveRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        User requester = new User(1L, "requester");
        User participant1 = new User(2L, "participant1");
        User participant2 = new User(3L, "participant2");
    }

    @Test
    void 정산_엔티티를_생성한다() {
        // Given
        User requestUser = new User(1L, "Request User");
        User receiveUser1 = new User(2L, "Receive User 1");
        User receiveUser2 = new User(3L, "Receive User 2");

        CreateRequest.RequestReceive receive1 = new CreateRequest.RequestReceive(2L, 50);
        CreateRequest.RequestReceive receive2 = new CreateRequest.RequestReceive(3L, 50);
        List<CreateRequest.RequestReceive> requestReceives = Arrays.asList(receive1, receive2);

        CreateRequest createRequest = new CreateRequest(1L, 100, requestReceives);

        when(userService.getUserById(1L)).thenReturn(requestUser);
        when(userService.getUserById(2L)).thenReturn(receiveUser1);
        when(userService.getUserById(3L)).thenReturn(receiveUser2);
        when(settlementRepository.save(any(Settlement.class))).thenAnswer(it -> it.getArgument(0));

        // When
        Settlement result = settlementService.request(createRequest);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(result.getRequestUser()).isEqualTo(requestUser);
            softly.assertThat(result.getTotalAmount()).isEqualTo(100);
            softly.assertThat(result.getSettlementReceives().getSettlementReceives()).hasSize(2);
            softly.assertThat(result.getSettlementReceives().getSettlementReceives().get(0).getUser()).isEqualTo(receiveUser1);
            softly.assertThat(result.getSettlementReceives().getSettlementReceives().get(1).getUser()).isEqualTo(receiveUser2);
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

}
