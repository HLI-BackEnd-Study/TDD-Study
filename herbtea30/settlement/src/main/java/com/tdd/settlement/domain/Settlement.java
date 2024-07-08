package com.tdd.settlement.domain;

import java.util.List;

/**
 * Name : Settlement <br/>
 * Description : Create a description.
 */
public class Settlement {
    private User owner;
    public Settlement(User owner) {
        this.owner = owner;
    }

    public void requestSettlement(Amount requestAmount, List<User> userList) {
    }

    public void requestSettlement(List<User> userList) {
    }
}
