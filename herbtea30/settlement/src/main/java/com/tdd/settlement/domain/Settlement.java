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
        return userList.stream().map(m -> {
            return new User(m.getId(), new Amount(requestAmount.getAmount()/userList.size()));
        }).toList();
    }
}
