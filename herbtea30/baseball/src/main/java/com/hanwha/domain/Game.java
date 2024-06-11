package com.hanwha.domain;

import com.hanwha.domain.Judge.Result;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Catcher catcher = new Catcher();
        while (true) {
            System.out.println("숫자를 입력해 주세요.");
            Scanner sc = new Scanner(System.in);
            try {
                Pitcher pitcher =  new Pitcher();

                String str = sc.next();
                Ball pitchBall = pitcher.pitch(Integer.parseInt(str));
                Result result = catcher.catchBall(pitchBall);

                System.out.println(result.getResultString());

                if (result.isStrikeOut()) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

                    if (sc.next().equals("2")) {
                        break;
                    }
                    catcher = new Catcher();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
