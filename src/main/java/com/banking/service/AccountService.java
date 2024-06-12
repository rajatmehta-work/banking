package com.banking.service;

import com.banking.dto.AccountDTO;

public interface AccountService {
    Boolean createAccount(AccountDTO accountDTO);// Can return Object defining the details of account created with status like KYC pending
    AccountDTO getAccount(Long accountId);
}
