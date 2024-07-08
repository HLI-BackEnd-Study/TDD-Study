package com.tdd.settlement.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SettlementTest {

    @DisplayName("정산 요청하기 - N빵")
    @Test
    public void test1() {
        User owner = new User("kdh");
        Settlement settlement = new Settlement(owner);
        Amount requestAmount = new Amount(100000L);
        List<User> userList = List.of(new User("leehj"), new User("kimmj"), new User("inch"));
        settlement.requestSettlement(requestAmount, userList);
    }

    @DisplayName("정산 요청하기 - 지정")
    @Test
    public void test1_1() {
        User owner = new User("kangdh");
        Settlement settlement = new Settlement(owner);
        List<User> userList = List.of(new User("leehj", new Amount(25000L)), new User("kimmj", new Amount(25000L)), new User("inch", new Amount(20000L)));
        settlement.requestSettlement(userList);
    }

    @DisplayName("정산 금액 보내기")
    @Test
    public void test2() {
    }

    @DisplayName("정신 금액 받기")
    @Test
    public void test3() {
    }

    @DisplayName("정산 요청내역")
    @Test
    public void test4(){
    }

    @DisplayName("정산 보낸내역")
    @Test
    public void test5() {
    }
}
