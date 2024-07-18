package com.tdd.settlement.domain;

import com.tdd.settlement.exception.ExceptionMessage;
import com.tdd.settlement.exception.SettlementException;
import java.util.List;
import lombok.Getter;

/**
 * Name : Settlement <br/>
 * Description : Create a description.
 */
@Getter
public class Settlement {
    private final User owner;
    private final Amount requestAmount;
    private final List<User> userList;

    public Settlement(User owner, Amount requestAmount, List<User> userList) {
        this.owner = owner;
        this.requestAmount = requestAmount;
        this.userList = userList;
    }

    public List<User> requestSettlement() {
        if (this.requestAmount.getAmount() != this.userList.stream().mapToInt(m -> m.getRequestAmount().getAmount()).sum()) {
            throw new SettlementException(ExceptionMessage.SETTLEMENT_AMOUNT_NOT_MATCH);
        }
        this.owner.getRequestSettlements().add(this);
        return this.userList.stream().toList();
    }

    public List<User> requestDivSettlement() {
        return userList.stream().map(m ->  new User(m.getId(), new Amount(this.requestAmount.getAmount() / this.userList.size()))).toList();
    }
}
