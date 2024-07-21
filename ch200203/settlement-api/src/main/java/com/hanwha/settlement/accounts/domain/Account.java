package com.hanwha.settlement.accounts.domain;

import com.hanwha.settlement.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int balance;

    private Account(User user, int balance) {
        this.user = user;
        this.balance = balance;
    }

    public static Account create(User user, int balance) {
        return new Account(user, balance);
    }

    public void withdraw(int amount) {
        validateWithdraw(amount);
        this.balance -= amount;
    }

    private void validateWithdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금 금액은 0보다 커야 합니다.");
        }

        if (balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
    }

    public void deposit(int amount) {
        validateDeposit(amount);
        this.balance += amount;
    }

    private void validateDeposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금 금액은 0보다 커야 합니다.");
        }
    }

}
