package com.hanwha.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hanwha.domain.Judge.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JudgeTest {

    @Test
    @DisplayName("투수가 투구한 볼과 포수의 볼을 비교한다.")
    void test1() {
        Goal goal = new Goal();
        Ball pitchBall = new Ball(123);
        Ball goalBall = goal.getBall();

        Judge judge = new Judge(goalBall);
        assertThat(judge.compareBall(pitchBall)).isNotNull();
    }

    @Test
    @DisplayName("판정 관련 테스트")
    void test2() {
        Ball pitchBall = new Ball(123);
        Ball sameGoalBall = new Ball(145);

        Judge judge = new Judge(sameGoalBall);
        Result result = judge.compareBall(pitchBall);
        assertThat(result.getStrikeCount()).isEqualTo(1);
        assertThat(result.getBallCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("판정 관련 테스트 - 1스트라이트 2볼")
    void test3() {
        Ball pitchBall = new Ball(123);
        Ball sameGoalBall = new Ball(132);

        Judge judge = new Judge(sameGoalBall);
        Result result = judge.compareBall(pitchBall);
        assertThat(result.getStrikeCount()).isEqualTo(1);
        assertThat(result.getBallCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("판정 관련 테스트 - 스트라이크 아웃")
    void test4() {
        Ball pitchBall = new Ball(123);
        Ball sameGoalBall = new Ball(123);

        Judge judge = new Judge(sameGoalBall);
        Result result = judge.compareBall(pitchBall);
        assertThat(result.getStrikeCount()).isEqualTo(3);
        assertThat(result.getBallCount()).isEqualTo(0);
        assertThat(result.isStrikeOut()).isTrue();
    }

    @Test
    @DisplayName("판정 관련 테스트 - 낫씽")
    void test5() {
        Ball pitchBall = new Ball(123);
        Ball sameGoalBall = new Ball(456);

        Judge judge = new Judge(sameGoalBall);
        Result result = judge.compareBall(pitchBall);
        assertThat(result.getStrikeCount()).isEqualTo(0);
        assertThat(result.getBallCount()).isEqualTo(0);
        assertThat(result.isNothing()).isTrue();
    }

    @Test
    @DisplayName("판정 관련 테스트 - 낫씽")
    void test6() {
        assertThatThrownBy(() -> {
            Ball pitchBall = new Ball(0);
            Ball sameGoalBall = new Ball(456);

            Judge judge = new Judge(sameGoalBall);
            Result result = judge.compareBall(pitchBall);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("볼은 3자리 숫자만 입력할 수 없습니다.");
    }

}
