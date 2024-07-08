package com.tdd.settlement.domain;

import lombok.Getter;

/**
 * Name : Amount <br/>
 * Description : Create a description.
 */
@Getter
public class Amount {
    private int amount;

    public Amount(int amount) {
        this.amount = amount;
    }
}
