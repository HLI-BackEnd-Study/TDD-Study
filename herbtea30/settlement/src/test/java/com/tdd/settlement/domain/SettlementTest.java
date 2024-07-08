package com.tdd.settlement.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SettlementTest {

    @DisplayName("정산 요청하기 - N빵")
    @Test
    public void test1() {
        Settlement settlement = new Settlement();
        Amount requestAmount = new Amount(100000L);
        List<User> userList = List.of(new User("kdh"), new User("leehj"), new User("kimmj"), new User("inch"));
        settlement.requestSettlement(requestAmount, userList);
    }

    @DisplayName("정산 요청하기 - 지정")
    @Test
    public void test1() {
        Settlement settlement = new Settlement();
        List<User> userList = List.of(new User("kangdh", 30000L), new User("leehj", 25000L), new User("kimmj", 25000L), new User("inch", 20000L));
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
