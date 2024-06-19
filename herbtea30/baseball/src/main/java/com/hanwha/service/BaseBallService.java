package com.hanwha.service;

import com.hanwha.consts.Message;
import com.hanwha.domain.Announcer;
import com.hanwha.domain.Ball;
import com.hanwha.domain.Catcher;
import com.hanwha.domain.Game;
import com.hanwha.domain.Goal;
import com.hanwha.domain.Judge;
import com.hanwha.domain.Judge.Result;
import com.hanwha.domain.Pitcher;
import java.util.Scanner;

/**
 * 각 객체 간의 관계를 통해서 어플리케이션 로직을 구성하는 서비스 객체
 */
public class BaseBallService {
    Pitcher pitcher = new Pitcher();
    Judge judge = new Judge();
    Catcher catcher = new Catcher(judge);
    Goal goal = new Goal();
    Announcer announcer = new Announcer();

    /**
     * 게임을 플레이하는 메소드
     */
    public void play() {
        while (true) {
            //1. 아나운서가 입력 메시지를 출력한다.
            announcer.announceMessage(Message.INPUT);

            //2. 던질 공을 사용자가 입력한다.
            Scanner sc = new Scanner(System.in);
            try {
                String str = sc.next();
                //3. 입력 받은 값으로 투수가 공을 던진다.
                Ball pitchBall = pitcher.pitch(Integer.parseInt(str));

                //4. 포수가 공을 받아서, 심판에게 판정을 요청한다.
                Result result = catcher.catchBall(pitchBall);

                //5. 아나운서가 판정 결과를 출력한다.
                announcer.announceMessage(result.getResultString());

                //6. 스트라이크 아웃이면
                if (result.isStrikeOut()) {
                    //7. 아나운서가 스트라이크 아웃 메시지를 출력한다.
                    announcer.announceMessage(Message.STRIKE_OUT);
                    //8. 아나운서가 게임이 종료되었으므로, 재실행, 종료 커맨드 공지를 출력한다.
                    announcer.announceMessage(Message.COMMAND_NOTICE);

                    //9. 종료 커맨드 입력시 종료
                    if (sc.next().equals("2")) {
                        break;
                    }
                    //10. 재실행 커맨드 입력시, 새로운 게임을 시작한다.
                    Game.createNewGame(judge, goal);
                }
            } catch (Exception e) {
                //00. 아나운서가 오류 발생시 Exception 메시지로 출력한다.
                announcer.announceMessage(e.getMessage());
            }
        }
    }
}
