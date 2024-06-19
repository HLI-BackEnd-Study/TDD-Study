package com.hanwha.domain;

public class Pitcher {

    /**
     * 공 던지는 투수 객체
     * @param pitchingBall
     * @return
     */
    public Ball pitch(int pitchingBall) {
        return new Ball(pitchingBall);
    }
}
