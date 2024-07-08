package com.tdd.settlement.domain;

import lombok.Getter;

/**
 * Name : User <br/>
 * Description : Create a description.
 */
@Getter
public class User {
    private String id;
    private Amount requestAmount;

    public User(String id) {
        this.id = id;
    }

    public User(String id, Amount requestAmount) {
        this.id = id;
        this.requestAmount = requestAmount;
    }
}
