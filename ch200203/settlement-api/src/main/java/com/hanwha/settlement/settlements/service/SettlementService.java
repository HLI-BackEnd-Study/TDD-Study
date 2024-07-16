package com.hanwha.settlement.settlements.service;

import com.hanwha.settlement.settlements.dto.CreateRequest;
import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import com.hanwha.settlement.settlements.model.SettlementReceives;
import com.hanwha.settlement.settlements.repository.SettlementRepository;
import com.hanwha.settlement.users.User;
import com.hanwha.settlement.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final UserService userService;

    public Settlement request(CreateRequest request) {
        // 1. 정산 요청 유저
        User requestUser = userService.getUserById(request.userId());

        // 2. 정산 객체 생성
        Settlement settlement = Settlement.create(requestUser, request.totalAmount());

        // 2-1. 정산 요청 받을 유저 객체 생성
        SettlementReceives settlementReceives = createReceives(request.requestReceives(), settlement);

        // 2-2. 정산 요청 객체를 정산 객체에 추가
        settlement.addSettlementReceives(settlementReceives);

        // 3. 객체 저장
        return settlementRepository.save(settlement);
    }

    /**
     * 정산 요청받은 유저 객체 생성
     */
    private SettlementReceives createReceives(List<CreateRequest.RequestReceive> requestReceives, Settlement settlement) {
        List<SettlementReceive> settlementReceives = requestReceives.stream()
                .map(requestReceive -> {
                    User receivUser = userService.getUserById(requestReceive.userId());
                    return SettlementReceive.create(settlement, receivUser, requestReceive.amount());
                }).toList();

        return SettlementReceives.of(settlementReceives);
    }


}
