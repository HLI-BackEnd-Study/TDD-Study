package com.hanwha.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 랜덤 난수를 생성해서 Ball에 할당 해주는 객체
 */
public class Goal {
    private List<Integer> duplicationList = new ArrayList<>();
    private int randomNumber;
    private int firstNumber;
    private int secondNumber;
    private int thirdNumber;
    private static final int DUPLICATION_RANDOM_NUMBER_COUNT = 10;


    public Ball getBall() {
        return new Ball(generateRandomNumber());
    }

    /**
     * 임의의 난수 생성로직
     * 1~9까지의 수
     * 3자리 숫자
     * 각 자리수는 중복 되지 않는다.
     *
     * @return
     */
    private int generateRandomNumber() {
        randomNumber = getRandomNum();
        while (true) {
            if ((isRightRange(randomNumber) && isPositive() && isDifference()) && isNotEqualTempRandomNumber()) {
                break;
            } else {
                randomNumber = getRandomNum();
            }
        }
        return randomNumber;
    }

    /**
     * 직전 난수 리스트 추가
     * 호출 시점은 게임 다시 시작하기 할때 호출한다.
     */
    public void addDuplicationNumberList() {
        System.out.println(" duplication Check List :: "+ randomNumber + " // " + duplicationList);
        if (duplicationList.size() < DUPLICATION_RANDOM_NUMBER_COUNT) {
            duplicationList.add(randomNumber);
        } else {
            duplicationList.remove(0);
            duplicationList.add(randomNumber);
        }
    }

    /**
     * 이전 난수리스트에 보함되어있지 않다면 true
     * 포함 되어 있다면 false
     * @return
     */
    private boolean isNotEqualTempRandomNumber() {
        return !duplicationList.contains(randomNumber);
    }

    /**
     * 3자리 난수 생성(100 ~ 999)
     * 각 자리수 별 설정
     * @return
     */
    private int getRandomNum() {
        int randomNum = (int) (Math.random() * 1000);
        firstNumber = randomNum / 100;
        secondNumber = (randomNum - (100 * firstNumber)) / 10;
        thirdNumber = randomNum - (100 * firstNumber) - (10 * secondNumber);
        return randomNum;
    }

    /**
     * 각 자리 수가 다른지 여부
     * 모두 다르면 true
     * 하나라도 같으면 false
     * @return
     */
    private boolean isDifference() {
        return firstNumber != secondNumber && secondNumber != thirdNumber && firstNumber != thirdNumber;
    }

    /**
     * 각 자리 수가 양수 여부
     * @return
     */
    private boolean isPositive() {
        return firstNumber > 0 && secondNumber > 0 && thirdNumber > 0;
    }

    /**
     * 난수가 100 ~ 999 까지에 속하는지
     * @param randomNum
     * @return
     */
    private boolean isRightRange(int randomNum) {
        return randomNum > 99 || randomNum < 1000;
    }

    public List<Integer> getDuplicationList() {
        return duplicationList;
    }
}
