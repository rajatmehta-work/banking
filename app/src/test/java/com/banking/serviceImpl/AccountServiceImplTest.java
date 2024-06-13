package com.banking.serviceImpl;

import com.banking.dto.AccountDTO;
import com.banking.entity.Account;
import com.banking.enums.AccountStatus;
import com.banking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Mock
    private AccountRepository accountRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount_Success() {
        AccountDTO accountDTO = AccountDTO.builder()
                                .documentNumber(BigDecimal.TEN)
                                .build();
        when(accountRepository.findByDocumentNumber(any(BigDecimal.class))).thenReturn(Optional.empty());
        when(accountRepository.save(any(Account.class))).thenReturn(Account.builder().documentNumber(BigDecimal.TEN).status(AccountStatus.ACTIVATED).build());

        AccountStatus result = accountServiceImpl.createAccount(accountDTO);
        assertEquals(result, AccountStatus.ACTIVATED);
    }

    @Test
    public void testCreateAccount_Conflict() {
        AccountDTO accountDTO = AccountDTO.builder()
                                .documentNumber(BigDecimal.TEN)
                                .build();
        when(accountRepository.findByDocumentNumber(any(BigDecimal.class))).thenReturn(Optional.of(Account.builder().documentNumber(BigDecimal.TEN).status(AccountStatus.KYC_PENDING).build()));
        when(accountRepository.save(any(Account.class))).thenReturn(new Account());

        AccountStatus result = accountServiceImpl.createAccount(accountDTO);
        assertEquals(AccountStatus.KYC_PENDING, result);
    }

    @Test
    public void testGetAccount_Found() {
        Account account = Account.builder().id(1l).build();
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        AccountDTO result = accountServiceImpl.getAccount(1L);
        assertEquals(AccountDTO.fromAccount(account).getAccountId(), result.getAccountId());
    }

    @Test
    public void testGetAccount_NotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        AccountDTO result = accountServiceImpl.getAccount(2L);
        assertEquals(result, null);
    }
}
