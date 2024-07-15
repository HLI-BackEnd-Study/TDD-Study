package com.hanwha.settlement.settlements.service;

import com.hanwha.settlement.settlements.repository.SettlementRepository;
import com.hanwha.settlement.users.User;
import com.hanwha.settlement.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SettlementServiceTest {

    @InjectMocks
    private SettlementService settlementService;

    @Mock
    private SettlementRepository settlementRepository;

    @Mock
    private UserRepository userRepository;

    private User requester;
    private User participant1;
    private User participant2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        requester = new User(1L, "requester");
        participant1 = new User(2L, "participant1");
        participant2 = new User(3L, "participant2");
    }

    @Test
    void 정산_엔티티를_생성한다() {

    }



}
