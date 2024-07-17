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
    private User owner;
    private Amount requestAmount;

    public Settlement(User owner, Amount requestAmount) {
        this.owner = owner;
        this.requestAmount = requestAmount;
    }

    public List<User> requestSettlement(List<User> userList) {
        if (this.requestAmount.getAmount() != userList.stream().mapToInt(m -> m.getRequestAmount().getAmount()).sum()) {
            throw new SettlementException(ExceptionMessage.SETTLEMENT_AMOUNT_NOT_MATCH);
        }
        this.owner.getRequestSettlements().add(this);
        return userList.stream().toList();
    }

    public List<User> requestDivSettlement(List<User> userList) {
        return userList.stream().map(m ->  new User(m.getId(), new Amount(this.requestAmount.getAmount() / userList.size()))).toList();
    }
}
