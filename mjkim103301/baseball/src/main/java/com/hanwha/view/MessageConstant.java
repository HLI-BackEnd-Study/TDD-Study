package com.hanwha.view;

public class MessageConstant {

    private MessageConstant() {
    }

    static final String USER_INPUT = "1~9까지의 서로 다른 3자리 수로 구성해주세요.:";
    static final String SUCCESS = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    static final String RETRY = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    static final String INPUT_MISMATCH = "잘못된 입력입니다. 숫자를 입력해 주세요.";
    static final String BALL = "볼";
    static final String STRIKE = "스트라이크";
    static final String NOTHING = "낫싱";

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
