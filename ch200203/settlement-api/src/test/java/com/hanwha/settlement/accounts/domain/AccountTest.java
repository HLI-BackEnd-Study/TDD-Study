package com.hanwha.settlement.accounts.domain;

import com.hanwha.settlement.users.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class AccountTest {

    @ParameterizedTest
    @CsvSource(value = {"500:500", "1000:0", "10:990"}, delimiter = ':')
    void 출금을_진행하면_예수금을_출금액만큼_차감한다(int amount, int expected) {
        // Given
        User user = new User(1L, "ich");
        Account account = Account.create(user, 1_000);

        // When
        account.withdraw(amount);

        // Then
        assertThat(account.getBalance()).isEqualTo(expected);
    }

    @Test
    void 출금시_잔액이_부족한_경우_예외를_반환한다() {
        // Given
        User user = new User(1L, "ich");
        Account account = Account.create(user, 1_000);
        int amount = 1_500;

        // Then
        assertThatThrownBy(() -> account.withdraw(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잔액이 부족합니다.");
    }

    @Test
    void 출금액이_0_이하_경우_예외를_반환한다() {
        // Given
        User user = new User(1L, "ich");
        Account account = Account.create(user, 1_000);
        int amount = 0;

        // Then
        assertThatThrownBy(() -> account.withdraw(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출금 금액은 0보다 커야 합니다.");
    }

    @Test
    void 입금_후_잔액은_입금_금액만큼_증가합니다() {
        // Given
        User user = new User(1L, "ich");
        Account account = Account.create(user, 1_000);
        int amount = 1_000;

        // When
        account.deposit(amount);

        // Then
        assertThat(account.getBalance()).isEqualTo(2_000);
    }

    @Test
    void 입금_금액이_0_이하인_경우_예외를_반환한다() {
        User user = new User(1L, "ich");
        Account account = Account.create(user, 1_000);
        int amount = 0;

        // When & Then
        assertThatThrownBy(() -> account.deposit(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입금 금액은 0보다 커야 합니다.");
    }

}
