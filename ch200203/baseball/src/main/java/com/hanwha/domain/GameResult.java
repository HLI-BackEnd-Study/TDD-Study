package com.hanwha.domain;

import static com.hanwha.constant.BaseballConstant.GAME_COUNT;
import static com.hanwha.constant.MessageConstant.*;

public class GameResult {

    private final int strikes;
    private final int balls;

    public GameResult(int strikes, int balls) {
        this.strikes = strikes;
        this.balls = balls;
    }

    public boolean isWrong() {
        return strikes != GAME_COUNT;
    }

    private String formatGameResult(int strikes, int balls) {
        if (strikes == 0 && balls == 0) {
            return NOTHING;
        }

        StringBuilder result = new StringBuilder();
        appendIfPositive(result, strikes, STRIKE);
        appendIfPositive(result, balls, BALL);

        return result.toString().trim();
    }

    private void appendIfPositive(StringBuilder result, int count, String label) {
        if (count > 0) {
            if (!result.isEmpty()) {
                result.append(SPACE);
            }
            result.append(String.format(label, count));
        }
    }

    public String toString() {
        return formatGameResult(strikes, balls);
    }
}
