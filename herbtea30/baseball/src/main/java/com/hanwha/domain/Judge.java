package com.hanwha.domain;

import java.util.List;

/**
 * 투수가 던진 공을 판정하는 심판 객체
 */
public class Judge {
    private Ball goalBall;

    public Judge() {
        Goal goal = new Goal();
        this.goalBall = goal.getRandomBall();
    }

    public Judge(Ball goalBall) {
        this.goalBall = goalBall;
    }

    /**
     * 판별 메소드
     * @param pitchBall
     * @return 판별
     */
    public Result compareBall(Ball pitchBall) {
        return new Result(getStrikeCount(pitchBall), getBallCount(pitchBall));
    }

    /**
     * 볼 판별하는 메소드
     * @param pitchBall
     * @return
     */
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

    /**
     * 스트라이크 판별 메소드
     * @param pitchBall
     * @return 스트라이크 수
     */
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

    /**
     * 야구게임 다시 진행시, GoalBall 생성
     * @param randomBall
     */
    public void setGoalBall(Ball randomBall) {
        goalBall = randomBall;
    }

    /**
     * 야구게 임 결과 객체
     */
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
