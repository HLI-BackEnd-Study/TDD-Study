package com.tdd.settlement.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

@SpringBootTest
public class SettlementTest {

    @DisplayName("정산 요청하기 - N빵")
    @Test
    public void test1() {
        User owner = new User("kangdh");
        Amount requestAmount = new Amount(70000);
        List<User> userList = List.of(new User("leehj"), new User("kimmj"), new User("inch"));
        Settlement settlement = new Settlement(owner, userList, requestAmount);
        List<User> settlementRequestList = settlement.requestDivSettlement();
        assertThat(settlementRequestList).hasSize(3);
        assertThat(settlementRequestList.get(0).getId()).isEqualTo("leehj");
        assertThat(settlementRequestList.get(0).getRequestAmount().getAmount()).isEqualTo(23333);
        assertThat(settlement.getOwner().getId()).isEqualTo("kangdh");
    }

    @DisplayName("정산 요청하기 - 지정")
    @Test
    public void test1_1() {
        User owner = new User("kangdh");
        Amount requestAmount = new Amount(70000);
        List<User> userList = List.of(new User("leehj", new Amount(25000)), new User("kimmj", new Amount(25000)), new User("inch", new Amount(20000)));
        Settlement settlement = new Settlement(owner, userList, requestAmount);
        List<User> settlementRequestList = settlement.requestSettlement();
        assertThat(settlementRequestList).hasSize(3);
        assertThat(settlementRequestList.get(0).getId()).isEqualTo("leehj");
        assertThat(settlementRequestList.get(0).getRequestAmount().getAmount()).isEqualTo(25000);
        assertThat(settlementRequestList.get(1).getRequestAmount().getAmount()).isEqualTo(25000);
        assertThat(settlementRequestList.get(2).getRequestAmount().getAmount()).isEqualTo(20000);
        assertThat(settlementRequestList.stream().mapToInt(m -> m.getRequestAmount().getAmount()).sum()).isEqualTo(requestAmount.getAmount());
        assertThat(settlement.getOwner().getId()).isEqualTo("kangdh");
    }

    @DisplayName("정산 요청하기 - 지정 금액 합계 오류")
    @Test
    public void test1_2() {
        User owner = new User("kangdh");
        Amount requestAmount = new Amount(80000);
        List<User> userList = List.of(new User("leehj", new Amount(25000)), new User("kimmj", new Amount(25000)), new User("inch", new Amount(20000)));
        Settlement settlement = new Settlement(owner, userList, requestAmount);
        assertThatThrownBy(() -> {
            settlement.requestSettlement();
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("정산 요청금액과 세부 금액 합계가 맞지 않습니다.");

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
