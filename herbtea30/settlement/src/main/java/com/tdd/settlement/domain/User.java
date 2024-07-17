package com.tdd.settlement.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Name : User <br/>
 * Description : Create a description.
 */
@Getter
public class User {
    private String id;
    private Amount requestAmount;
    private List<Settlement> requestSettlements;
    private List<Settlement> responseSettlements;

    public User(String id) {
        this.id = id;
        this.requestSettlements = new ArrayList<>();
        this.responseSettlements = new ArrayList<>();
    }

    public User(String id, Amount requestAmount) {
        this.id = id;
        this.requestAmount = requestAmount;
        this.requestSettlements = new ArrayList<>();
        this.responseSettlements = new ArrayList<>();
    }

    public List<Settlement> getRequestSettlements() {
        return this.requestSettlements;
    }

    public List<Settlement> getResponseSettlements() {
        return this.responseSettlements;
    }
}
