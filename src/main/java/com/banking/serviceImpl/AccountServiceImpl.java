package com.banking.serviceImpl;

import com.banking.dto.AccountDTO;
import com.banking.entity.Account;
import com.banking.enums.AccountStatus;
import com.banking.repository.AccountRepository;
import com.banking.repository.UserRepository;
import com.banking.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Data
public class AccountServiceImpl implements AccountService {

    @Autowired private AccountRepository accountRepository;

    @Autowired private UserRepository userRepository;

    @Override
    public AccountStatus createAccount(AccountDTO accountDTO) {

        Optional<Account> account = accountRepository.findById(accountDTO.getAccountId());
        if(account.isPresent()){
            return account.get().getStatus();
        }

        //Initiate KYC process in async way.....

        return accountRepository.save(Account.fromAccountDTO(accountDTO))!=null?AccountStatus.PENDING :AccountStatus.FAILED;
    }

    @Override
    public AccountDTO getAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(!account.isPresent()){
            return null;
        }
        return AccountDTO.fromAccount(account.get());
    }

}
