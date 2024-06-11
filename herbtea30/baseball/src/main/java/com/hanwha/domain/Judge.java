package com.hanwha.domain;

import java.util.List;

public class Judge {
    private final Ball goalBall;
    public Judge(Ball goalBall) {
        this.goalBall = goalBall;
    }

    public Result compareBall(Ball pitchBall) {
        int strikeCount = getStrikeCount(pitchBall);
        int ballCount = getBallCount(pitchBall);

        return new Result(strikeCount, ballCount);
    }

    private int getBallCount(Ball pitchBall) {
        int ballCount = 0;
        List<Integer> goalBallList = goalBall.getBalls();
        List<Integer> pitchBallList = pitchBall.getBalls();
        for (Integer num : pitchBallList) {
            if(goalBallList.contains(num) && goalBallList.indexOf(num) != pitchBallList.indexOf(num)) {
                ballCount++;
            }
        }
        return ballCount;
    }

    private int getStrikeCount(Ball pitchBall) {
        int strikeCount = 0;
        List<Integer> goalBallList = goalBall.getBalls();
        List<Integer> pitchBallList = pitchBall.getBalls();
        for (Integer num : pitchBallList) {
            if(goalBallList.indexOf(num) == pitchBallList.indexOf(num)) {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    public static class Result {
        private int strikeCount;
        private int ballCount;

        public int getStrikeCount() {
            return strikeCount;
        }

        public void setStrikeCount(int strikeCount) {
            this.strikeCount = strikeCount;
        }

        public int getBallCount() {
            return ballCount;
        }

        public void setBallCount(int ballCount) {
            this.ballCount = ballCount;
        }

        public boolean isStrikeOut() {
            return strikeCount == 3;
        }

        public Result(int strikeCount, int ballCount) {
            this.strikeCount = strikeCount;
            this.ballCount = ballCount;
        }

        public Result() {
        }

        public boolean isNothing() {
            return strikeCount == 0  && ballCount == 0;
        }

        public String getResultString() {
            String result = "";

            if (ballCount > 0) {
                result = result + ballCount + "볼 ";
            }

            if (strikeCount > 0) {
                result = result + strikeCount + "스트라이크";
            }

            if (strikeCount == 0 && ballCount == 0) {
                result = "낫싱";
            }
            return result;
        }
    }
}
