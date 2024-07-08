package com.tdd.settlement.domain;

import java.util.List;

/**
 * Name : Settlement <br/>
 * Description : Create a description.
 */
public class Settlement {
    private User owner;
    private Amount requestAmount;

    public Settlement(User owner, Amount requestAmount) {
        this.owner = owner;
        this.requestAmount = requestAmount;
    }

    public User getOwner() {
        return owner;
    }

    public List<User> requestSettlement(List<User> userList) {
        if (requestAmount.getAmount() != userList.stream().mapToInt(m -> m.getRequestAmount().getAmount()).sum()) {
            throw new RuntimeException("정산 요청금액과 세부 금액 합계가 맞지 않습니다.");
        }

        return userList.stream().map(m -> {
            if (m.getRequestAmount() == null) {
                return new User(m.getId(), new Amount(this.requestAmount.getAmount() / userList.size()));
            }
            return m;
        }).toList();
    }
}
