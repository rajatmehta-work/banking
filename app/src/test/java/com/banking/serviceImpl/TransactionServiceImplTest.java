package com.banking.serviceImpl;

import com.banking.dto.TransactionDTO;
import com.banking.entity.Account;
import com.banking.entity.Transaction;
import com.banking.entity.TransactionType;
import com.banking.enums.AccountStatus;
import com.banking.enums.TransactionStatus;
import com.banking.enums.TransactionTypeEnum;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.repository.TransactionTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionTypeRepository txnTypeRepository;

   

    @Test
    void testTransact_AccountNotActivated() {
        Account account = Account.builder()
                    .id(1L)
                    .balance(0.0)
                    .status(AccountStatus.PENDING).build();
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        TransactionDTO transactionDTO = TransactionDTO.builder()
                                            .accountId(1L)
                                            .amount(100.0)
                                            .operationTypeId(1)
                                            .build();

        TransactionStatus status = transactionService.transact(transactionDTO);
        assertEquals(TransactionStatus.CANCELED, status);
    }

    @Test
    void testTransact_InsufficientBalance() {
        Account account = Account.builder()
                    .id(1L)
                    .balance(10.0)
                    .status(AccountStatus.ACTIVATED).build();
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        when(txnTypeRepository.findById(anyInt())).thenReturn((Optional.of(TransactionType.builder().build())));

       TransactionDTO transactionDTO = TransactionDTO.builder()
                                            .accountId(1L)
                                            .amount(100.0)
                                            .operationTypeId(1)
                                            .build();

        TransactionStatus status = transactionService.transact(transactionDTO);
        assertEquals(TransactionStatus.CANCELED, status);
    }

    @Test
    void testTransact_SuccessfulTransaction() {
        Account account = Account.builder()
                    .id(1L)
                    .balance(10.0)
                    .status(AccountStatus.ACTIVATED).build();
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        when(txnTypeRepository.findById(anyInt())).thenReturn((Optional.of(TransactionType.builder().id(4).type(TransactionTypeEnum.CREDIT_VOUCHER).build())));
        when(accountRepository.updateBalance(anyDouble(), anyLong(), anyDouble())).thenReturn(1);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(Transaction.builder().build());

        TransactionDTO transactionDTO = TransactionDTO.builder()
                                            .accountId(1L)
                                            .amount(100.0)
                                            .operationTypeId(4)
                                            .build();

        TransactionStatus status = transactionService.transact(transactionDTO);
        assertEquals(TransactionStatus.DONE, status);
    }

    @Test
    void testTransact_Failed_Update_Balance() {
        Account account = Account.builder()
                    .id(1L)
                    .balance(10.0)
                    .status(AccountStatus.ACTIVATED).build();
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        when(txnTypeRepository.findById(anyInt())).thenReturn((Optional.of(TransactionType.builder().id(4).type(TransactionTypeEnum.CREDIT_VOUCHER).build())));
        when(accountRepository.updateBalance(anyDouble(), anyLong(), anyDouble())).thenReturn(0);
        
        TransactionDTO transactionDTO = TransactionDTO.builder()
                                            .accountId(1L)
                                            .amount(100.0)
                                            .operationTypeId(4)
                                            .build();

        TransactionStatus status = transactionService.transact(transactionDTO);
        assertEquals(TransactionStatus.FAILED, status);
    }
}
