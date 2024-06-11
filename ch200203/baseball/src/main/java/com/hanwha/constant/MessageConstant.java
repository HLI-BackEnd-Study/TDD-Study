package com.hanwha.constant;

public class MessageConstant {

    public static String NUMBER_BOUND_EXCEPTION = "숫자는 1~9까지 입니다.";
    public static String DUPLICATE_NUMBER_EXCEPTION = "중복된 숫자가 있습니다.";
    public static final String NUMBER_COUNT_EXCEPTION = "숫자는 3개여야 합니다.";
    public static final String INPUT_NUMBER_COUNT_EXCEPTION = "입력은 %d 자리 숫자여야 합니다.";
    public static final String INVALID_INPUT_EXCEPTION = "올바르지 않은 입력입니다. 다시 확인해주세요.";
    public static final String INVALID_COMMAND_EXCEPTION = "유효하지 않은 명령어입니다: %d";


    private static final String START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    public static final String INPUT_MESSAGE = "숫자를 입력해주세요. : ";
    public static final String SUCCESS_MESSAGE = "숫자를 모두 맞추셨습니다.";

    public static final String NOTHING = "낫싱";
    public static final String STRIKE = "%d 스트라이크";
    public static final String BALL = "%d 볼";
    public static final String SPACE = " ";

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
