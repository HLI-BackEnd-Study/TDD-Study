package com.hanwha.consts;

public class Message {
    private Message() {
    }
    public static final String INPUT = "숫자를 입력해 주세요.";
    public static final String STRIKE_OUT = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    public static final String COMMAND_NOTICE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    public static final String BALL_HAS_THREE_DIGITS = "볼은 3자리 숫자만 입력할 수 있습니다.";
    public static final String BALL_RANGE_1_TO_9 = "각 자리 수는  1 ~ 9 까지만 가능합니다.";
    public static final String DUPLICATION_NOT_ALLOWED = "각 자리 수는 중복될 수 업습니다.";
}
