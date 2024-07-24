package com.tdd.settlement.domain;

import com.tdd.settlement.exception.ExceptionMessage;
import com.tdd.settlement.exception.SettlementException;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class SettlementDetail {
    private final Amount requestTotalAmount;
    private final List<User> userList;

    public SettlementDetail(Amount requestTotalAmount, List<User> userList) {
        this.requestTotalAmount = requestTotalAmount;
        this.userList = userList;
    }

    public List<User> requestSettlement() {
        if (this.requestTotalAmount.getAmount() != this.userList.stream().mapToInt(m -> m.getRequestAmount().getAmount()).sum()) {
            throw new SettlementException(ExceptionMessage.SETTLEMENT_AMOUNT_NOT_MATCH);
        }
        return this.userList;
    }

    public List<User> requestDivSettlement() {
        return this.userList.stream()
            .map(m ->  new User(m.getId(), new Amount(this.requestTotalAmount.getAmount() / this.userList.size())))
            .toList();
    }

    public void sendSettlementAmount(String id) {
        User user = userList.stream()
            .filter(f -> id.equals(f.getId()))
            .findAny().orElseThrow(() -> new SettlementException(ExceptionMessage.NOT_FOUND_USER_ID));

        //금액을 보낸다.
        log.info("{} 님이, {}원을 송금하였습니다.", user.getId(), user.getRequestAmount().getAmount());
        user.setSend(Boolean.TRUE);

    }

    public SettlementDetail getSendSettlementDetail(String id) {
        User user = this.userList.stream()
                .filter(f -> f.getId().equals(id))
                .findAny().orElse(null);

        return user != null ? this : null;
    }
}
