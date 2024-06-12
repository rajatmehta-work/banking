package com.banking.service;

import com.banking.dto.TransactionDTO;
import com.banking.enums.TransactionStatus;

public interface TransactionService {
    TransactionStatus transact(TransactionDTO transactionDTO); // Can be implemented in reactive way which will update the TransactionStatus.
}
