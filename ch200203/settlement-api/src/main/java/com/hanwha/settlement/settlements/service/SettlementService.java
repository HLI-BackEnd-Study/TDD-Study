package com.hanwha.settlement.settlements.service;

import com.hanwha.settlement.settlements.dto.CreateRequest;
import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import com.hanwha.settlement.settlements.repository.SettlementRepository;
import com.hanwha.settlement.users.User;
import com.hanwha.settlement.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final UserRepository userRepository;

    public void request(CreateRequest request) {
        // 1. 정산 요청 유저
        User requestUser = findUser(request.userId());

        // 2. 정산 객체 생성
        Settlement settlement = Settlement.create(requestUser, request.totalAmount());

        // 2-1. 정산 요청 받을 유저 객체 생성
        createReceive(request.requestReceives(), settlement);

    }

    /**
     * 정산 요청받은 유저 객체 생성
     */
    private List<SettlementReceive> createReceive(List<CreateRequest.RequestReceive> requestReceives, Settlement settlement) {
        List<SettlementReceive> receives = new ArrayList<>();

        for (CreateRequest.RequestReceive receive : requestReceives) {
            User receiveUser = findUser(receive.userId());
            receives.add(SettlementReceive.create(settlement, receiveUser, receive.amount()));
        }

        return receives;
    }

    @Transactional(readOnly = true)
    public User findUser(final long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다 : " + id));
    }

}
