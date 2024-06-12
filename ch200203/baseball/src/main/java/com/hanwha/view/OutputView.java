package com.hanwha.view;

import static com.hanwha.constant.MessageConstant.*;

public class OutputView {

    public static void welcomeMessage() {
        printMessage(WELCOME_MESSAGE);
    }

    public static void gameResult(String result) {
        printMessage(result);
    }

    public static void gameSuccess() {
        printMessage(SUCCESS_MESSAGE);
    }

    public static void end() {
        printMessage(END_MESSAGE);
    }
}
