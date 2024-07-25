package com.hanwha.settlement.accounts.service;

import com.hanwha.settlement.accounts.domain.Account;
import com.hanwha.settlement.accounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * 구현 편의상 유저 당 계좌는 한개만 가지고 있는 걸로...
     * 출금 후 잔액을 반환합니다.
     */
    @Transactional
    public int withdraw(long userId, int amount) {
        Account account = accountRepository.findAccountByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("찾으려는 계좌가 존재하지 않습니다. : " + userId));
        account.withdraw(amount);
        return account.getBalance();
    }

    @Transactional
    public int deposit(long userId, int amount) {
        Account account = accountRepository.findAccountByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("찾으려는 계좌가 존재하지 않습니다. : " + userId));
        account.deposit(amount);
        return account.getBalance();
    }
}
