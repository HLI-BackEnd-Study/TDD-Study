package com.tdd.settlement.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {
    public static final String SETTLEMENT_AMOUNT_NOT_MATCH = "정산 요청금액과 세부 금액 합계가 맞지 않습니다.";
    public static final String NOT_FOUND_USER_ID = "정산 금액을 보낼 사용자 Id를 다시 확인 해 주세요.";
}
