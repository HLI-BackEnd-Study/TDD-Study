package com.hanwha.settlement.settlements.service;

import com.hanwha.settlement.accounts.service.AccountService;
import com.hanwha.settlement.settlements.dto.CreateRequest;
import com.hanwha.settlement.settlements.dto.SettlementReceivesResponse;
import com.hanwha.settlement.settlements.dto.SettlementResponse;
import com.hanwha.settlement.settlements.dto.TransferRequest;
import com.hanwha.settlement.settlements.mapper.SettlementMapper;
import com.hanwha.settlement.settlements.model.Settlement;
import com.hanwha.settlement.settlements.model.SettlementReceive;
import com.hanwha.settlement.settlements.model.SettlementReceives;
import com.hanwha.settlement.settlements.repository.SettlementReceiveRepository;
import com.hanwha.settlement.settlements.repository.SettlementRepository;
import com.hanwha.settlement.users.User;
import com.hanwha.settlement.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementReceiveRepository settlementReceiveRepository;
    private final SettlementRepository settlementRepository;
    private final UserService userService;
    private final AccountService accountService;
    private final SettlementMapper mapper;

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

    // 뭘 반환해줘야 할까....
    public void transferPay(Long settlementReceiveId, TransferRequest request) {
        // 1. 정산할 객체 확인
        SettlementReceive settlementReceive = settlementReceiveRepository.findById(settlementReceiveId).orElseThrow(() -> new IllegalArgumentException("정산할 객체를 찾을 수 없습니다."));

        // 2. 유저계좌에서 출금
        int withdrawResult = accountService.withdraw(request.userId(), request.amount());

        // 3. 전달할 유저에게 입금
        accountService.deposit(settlementReceive.getSettlement().getRequestUser().getId(), request.amount());

        // 4. 정산완료
        settlementReceive.paid(request.amount());

        // 5. 전체정산 확인
        Settlement settlement = settlementRepository.findById(settlementReceive.getId()).orElseThrow(() -> new IllegalArgumentException("정산 객체를 찾을 수 없습니다."));

        // 6. 전체 유저가 정산을 완료했는지 확인
        settlement.complete();
    }

    @Transactional(readOnly = true)
    public List<SettlementReceivesResponse> getSettlementReceives(Long receiveUserId) {
        List<SettlementReceive> settlementReceives = settlementReceiveRepository.findSettlementReceivesByUserId(receiveUserId);
        return settlementReceives.stream()
                .map(mapper::settlementReceiveToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SettlementResponse> getSettlements(Long userId) {
        // 1. 유저 조회
        User user = userService.getUserById(userId);
        // 2. 유저가 정산받을 목록을 조회하여 반환한다.
       List<Settlement> settlements = settlementRepository.findSettlementByRequestUser(user);

        return settlements.stream()
                .map(mapper::settlementToResponse)
                .toList();
    }


}
