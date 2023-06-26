package com.learncode.Service;

import org.springframework.stereotype.Service;

import com.learncode.Entity.Account;
import com.learncode.Repository.AccountDAO;

@Service
public class AccountService {

    private final AccountDAO accountRepository;

    public AccountService(AccountDAO accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }
    
    // Các phương thức xử lý khác
}

