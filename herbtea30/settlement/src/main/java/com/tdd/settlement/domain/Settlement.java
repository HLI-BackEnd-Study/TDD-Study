package com.tdd.settlement.domain;

import com.tdd.settlement.exception.ExceptionMessage;
import com.tdd.settlement.exception.SettlementException;
import java.util.List;
import lombok.Getter;

/**
 * Name : Settlement <br/>
 * Description : Create a description.
 */
public class Settlement {
    @Getter
    private User owner;
    private Amount requestAmount;
    private List<User> userList;

    public Settlement(User owner, List<User> userList, Amount requestAmount) {
        this.owner = owner;
        this.requestAmount = requestAmount;
        this.userList = userList;
    }

    public List<User> requestSettlement() {
        if (this.requestAmount.getAmount() != this.userList.stream().mapToInt(m -> m.getRequestAmount().getAmount()).sum()) {
            throw new SettlementException(ExceptionMessage.SETTLEMENT_AMOUNT_NOT_MATCH);
        }
src/        return this.userList.stream().toList();
    }

    public List<User> requestDivSettlement() {
        return this.userList.stream().map(m ->  new User(m.getId(), new Amount(this.requestAmount.getAmount() / this.userList.size()))).toList();
    }
}
