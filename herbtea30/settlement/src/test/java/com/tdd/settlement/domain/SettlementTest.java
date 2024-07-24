package com.tdd.settlement.domain;

import com.tdd.settlement.exception.ExceptionMessage;
import com.tdd.settlement.exception.SettlementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

class SettlementTest {

    @DisplayName("정산 요청하기 - N빵")
    @Test
    void test1() {
        //정산요청자
        User owner = new User("kangdh");
        //정산 총액
        Amount requestAmount = new Amount(70000);

        //정산 대상에게 요청하기
        List<User> userList = List.of(new User("leehj"), new User("kimmj"), new User("inch"));
        Settlement settlement = new Settlement(owner, requestAmount, userList);
        List<User> settlementRequestList = settlement.requestDivSettlement();

        assertThat(settlementRequestList).hasSize(3);
        assertThat(settlementRequestList.get(0).getId()).isEqualTo("leehj");
        assertThat(settlementRequestList.get(0).getRequestAmount().getAmount()).isEqualTo(23333);
        assertThat(settlement.getOwner().getId()).isEqualTo("kangdh");
    }

    @DisplayName("정산 요청하기 - 지정")
    @Test
    void test1_1() {
        //정산요청자
        User owner = new User("kangdh");
        //정산 총액
        Amount requestAmount = new Amount(70000);

        //정산 대상에게 요청하기
        List<User> userList = List.of(new User("leehj", new Amount(25000)), new User("kimmj", new Amount(25000)), new User("inch", new Amount(20000)));
        Settlement settlement = new Settlement(owner, requestAmount, userList);
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
    void test1_2() {
        User owner = new User("kangdh");
        Amount requestAmount = new Amount(80000);
        List<User> userList = List.of(new User("leehj", new Amount(25000)), new User("kimmj", new Amount(25000)), new User("inch", new Amount(20000)));
        Settlement settlement = new Settlement(owner, requestAmount, userList);
        assertThatThrownBy(() -> settlement.requestSettlement()).isInstanceOf(SettlementException.class).hasMessageContaining(ExceptionMessage.SETTLEMENT_AMOUNT_NOT_MATCH);

    }

    @DisplayName("정산 요청내역")
    @Test
    void test5() {
        //정산요청자
        User owner = new User("kangdh");
        //정산 총액
        Amount requestAmount = new Amount(70000);

        //정산 대상에게 요청하기
        List<User> userList = List.of(new User("leehj", new Amount(25000)), new User("kimmj", new Amount(25000)), new User("inch", new Amount(20000)));
        Settlement settlement = new Settlement(owner, requestAmount, userList);
        settlement.requestSettlement();

        assertThat(owner.getRequestSettlements()).hasSize(1);
        assertThat(owner.getRequestSettlements().get(0).getRequestAmount().getAmount()).isEqualTo(70000);
        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList()).hasSize(userList.size());
        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList().stream().mapToInt(m -> m.getRequestAmount().getAmount()).sum()).isEqualTo(requestAmount.getAmount());
        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList().get(0).isSend()).isEqualTo(Boolean.FALSE);

    }

    @DisplayName("정산 금액 보내기")
    @Test
    void test6() {
        //정산요청자
        User owner = new User("kangdh");
        //정산 총액
        Amount requestAmount = new Amount(70000);

        //정산 대상에게 요청하기
        List<User> userList = List.of(new User("leehj", new Amount(25000)), new User("kimmj", new Amount(25000)), new User("inch", new Amount(20000)));
        Settlement settlement = new Settlement(owner, requestAmount, userList);
        settlement.requestSettlement();

        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList().get(0).isSend()).isEqualTo(Boolean.FALSE);
        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList().get(1).isSend()).isEqualTo(Boolean.FALSE);
        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList().get(2).isSend()).isEqualTo(Boolean.FALSE);

        settlement.sendSettlementAmount("leehj");
        settlement.sendSettlementAmount("kimmj");
        settlement.sendSettlementAmount("inch");

        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList().get(0).isSend()).isEqualTo(Boolean.TRUE);
        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList().get(1).isSend()).isEqualTo(Boolean.TRUE);
        assertThat(owner.getRequestSettlements().get(0).getSettlementDetail().getUserList().get(2).isSend()).isEqualTo(Boolean.TRUE);

        assertThatThrownBy(() -> settlement.sendSettlementAmount("leehj2")).isInstanceOf(SettlementException.class).hasMessageContaining(ExceptionMessage.NOT_FOUND_USER_ID);
        assertThatThrownBy(() -> settlement.sendSettlementAmount("kimmj3")).isInstanceOf(SettlementException.class).hasMessageContaining(ExceptionMessage.NOT_FOUND_USER_ID);
        assertThatThrownBy(() -> settlement.sendSettlementAmount("inch4")).isInstanceOf(SettlementException.class).hasMessageContaining(ExceptionMessage.NOT_FOUND_USER_ID);
    }

    @DisplayName("정산 보낸 내역")
    @Test
    void test7() {
        //정산요청자
        User owner = new User("kangdh");
        //정산 총액
        Amount requestAmount = new Amount(70000);

        //정산 대상에게 요청하기
        List<User> userList = List.of(new User("leehj", new Amount(25000)), new User("kimmj", new Amount(25000)), new User("inch", new Amount(20000)));
        Settlement settlement = new Settlement(owner, requestAmount, userList);
        settlement.requestSettlement();

        settlement.sendSettlementAmount("leehj");
        settlement.sendSettlementAmount("kimmj");
        settlement.sendSettlementAmount("inch");

        SettlementDetail settlementDetail = settlement.getSendSettlementDetail("leehj");
        assertThat(settlementDetail.getUserList().stream().filter(f -> f.getId().equals("leehj")).count()).isEqualTo(1);
        assertThat(settlementDetail.getUserList().stream().filter(f -> f.getId().equals("leehj")).findAny().get().getRequestAmount().getAmount()).isEqualTo(25000);
        assertThat(settlementDetail.getUserList().stream().filter(f -> f.getId().equals("kimmj")).count()).isEqualTo(1);
        assertThat(settlementDetail.getUserList().stream().filter(f -> f.getId().equals("kimmj")).findAny().get().getRequestAmount().getAmount()).isEqualTo(25000);
        assertThat(settlementDetail.getUserList().stream().filter(f -> f.getId().equals("inch")).count()).isEqualTo(1);
        assertThat(settlementDetail.getUserList().stream().filter(f -> f.getId().equals("inch")).findAny().get().getRequestAmount().getAmount()).isEqualTo(20000);
    }

}
