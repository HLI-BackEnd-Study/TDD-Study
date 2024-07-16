package com.tdd.settlement.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {
    public static final String SETTLEMENT_AMOUNT_NOT_MATCH = "정산 요청금액과 세부 금액 합계가 맞지 않습니다.";

}
