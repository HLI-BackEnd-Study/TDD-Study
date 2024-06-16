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

public class BaseBallService {
    Pitcher pitcher = new Pitcher();
    Judge judge = new Judge();
    Catcher catcher = new Catcher(judge);
    Goal goal = new Goal();
    Announcer announcer = new Announcer();

    public void play() {
        while (true) {
            announcer.announceMessage(Message.INPUT);
            Scanner sc = new Scanner(System.in);
            try {
                String str = sc.next();
                Ball pitchBall = pitcher.pitch(Integer.parseInt(str));
                Result result = catcher.catchBall(pitchBall);
                announcer.announceMessage(result.getResultString());

                if (result.isStrikeOut()) {
                    announcer.announceMessage(Message.STRIKE_OUT);
                    announcer.announceMessage(Message.COMMAND_NOTICE);
                    if (sc.next().equals("2")) {
                        break;
                    }
                    Game.createNewGame(judge, goal);
                }
            } catch (Exception e) {
                announcer.announceMessage(e.getMessage());
            }
        }
    }
}
