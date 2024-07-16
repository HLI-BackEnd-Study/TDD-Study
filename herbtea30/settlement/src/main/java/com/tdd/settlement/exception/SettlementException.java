package com.tdd.settlement.exception;

public class SettlementException extends RuntimeException {

    private String code;
    private String message;

    public SettlementException(String message) {
        this.message = message;
    }

    public SettlementException() {
        this.message = "Not yet implemented";
    }

    public SettlementException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public SettlementException(Exception ex) {
        super(ex);
    }
}
