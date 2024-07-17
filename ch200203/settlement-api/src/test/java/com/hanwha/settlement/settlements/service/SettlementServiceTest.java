package com.hanwha.settlement.settlements.service;

import com.hanwha.settlement.settlements.dto.CreateRequest;
import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import com.hanwha.settlement.settlements.repository.SettlementRepository;
import com.hanwha.settlement.users.User;
import com.hanwha.settlement.users.repository.UserRepository;
import com.hanwha.settlement.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SettlementServiceTest {

    @InjectMocks
    private SettlementService settlementService;

    @Mock
    private SettlementRepository settlementRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        // FIXME
    }



}
