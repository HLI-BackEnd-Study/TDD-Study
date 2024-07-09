package com.tdd.settlement.domain;

import java.util.List;

/**
 * Name : Settlement <br/>
 * Description : Create a description.
 */
public class Settlement {
    private User owner;
    private Amount requestAmount;
    private List<User> userList;

    public Settlement(User owner, List<User> userList, Amount requestAmount) {
        this.owner = owner;
        this.requestAmount = requestAmount;
        this.userList = userList;
    }

    public User getOwner() {
        return owner;
    }

    public List<User> requestSettlement() {
        if (this.requestAmount.getAmount() != this.userList.stream().mapToInt(m -> m.getRequestAmount().getAmount()).sum()) {
            throw new RuntimeException("정산 요청금액과 세부 금액 합계가 맞지 않습니다.");
        }

        return this.userList.stream().toList();
    }

    public List<User> requestDivSettlement() {
        return this.userList.stream().map(m ->  new User(m.getId(), new Amount(this.requestAmount.getAmount() / this.userList.size()))).toList();
    }
}
