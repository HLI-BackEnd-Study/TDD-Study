package com.hanwha.domain;

import java.util.ArrayList;
import java.util.List;

public class Goal {
    private List<Integer> duplicationList = new ArrayList<>();
    private int randomNumber;
    private int firstNumber;
    private int secondNumber;
    private int thirdNumber;
    private static final int DUPLICATION_RANDOM_NUMBER_COUNT = 10;
    public int generateRandomNumber() {
        randomNumber = getRandomNum();
        while (true) {
            if ((isRightRange(randomNumber) && isPositive() && isDifference()) && isNotEqualTempRandomNumber()) {
                break;
            } else {
                randomNumber = getRandomNum();
            }
        }
        addDuplicationNumberList();
        return randomNumber;
    }

    private void addDuplicationNumberList() {
        System.out.println(" duplication Check List :: "+ randomNumber + " // " + duplicationList);
        if (duplicationList.size() < DUPLICATION_RANDOM_NUMBER_COUNT) {
            duplicationList.add(randomNumber);
        } else {
            duplicationList.remove(0);
            duplicationList.add(randomNumber);
        }
    }

    private boolean isNotEqualTempRandomNumber() {
        return !duplicationList.contains(randomNumber);
    }

    private int getRandomNum() {
        int randomNum = (int) (Math.random() * 1000);
        firstNumber = randomNum / 100;
        secondNumber = (randomNum - (100 * firstNumber)) / 10;
        thirdNumber = randomNum - (100 * firstNumber) - (10 * secondNumber);
        return randomNum;
    }

    private boolean isDifference() {
        return firstNumber != secondNumber && secondNumber != thirdNumber && firstNumber != thirdNumber;
    }

    private boolean isPositive() {
        return firstNumber > 0 && secondNumber > 0 && thirdNumber > 0;
    }

    private boolean isRightRange(int randomNum) {
        return randomNum > 99 || randomNum < 999;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public int getThirdNumber() {
        return thirdNumber;
    }

    public List<Integer> getDuplicationList() {
        return duplicationList;
    }
}
