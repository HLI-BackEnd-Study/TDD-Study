package com.tdd.settlement.domain;

import lombok.Getter;

/**
 * Name : Amount <br/>
 * Description : Create a description.
 */
@Getter
public class Amount {
    private long totalAmount;

    public Amount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
