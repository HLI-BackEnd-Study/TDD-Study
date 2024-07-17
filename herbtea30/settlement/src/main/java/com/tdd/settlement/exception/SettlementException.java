package com.tdd.settlement.exception;

public class SettlementException extends RuntimeException {


    public SettlementException(String message) {
        super(message);
    }

    public SettlementException(Exception ex) {
        super(ex);
    }
}
