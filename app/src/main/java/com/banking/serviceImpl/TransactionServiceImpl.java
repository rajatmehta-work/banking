package com.banking.serviceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.banking.dto.TransactionDTO;
import com.banking.entity.Account;
import com.banking.entity.Transaction;
import com.banking.entity.TransactionType;
import com.banking.enums.AccountStatus;
import com.banking.enums.TransactionStatus;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.repository.TransactionTypeRepository;
import com.banking.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired private TransactionRepository transactionRepository;

    @Autowired private AccountRepository accountRepository;
    @Autowired private TransactionTypeRepository txnTypeRepository;

    @Transactional(rollbackFor = Exception.class)// can be added more conditions to handle properly rollback.
    @Override
    public TransactionStatus transact(TransactionDTO txn) {
        
        // Can add more layers of checks...

        Optional<Account> account = accountRepository.findById(txn.getAccountId());
        // .orElseThrow(() -> new ResourceNotFoundException("Account not found")); //Can throw global error, which will return with status code.

        if(account.get().getStatus() != AccountStatus.ACTIVATED){
            return TransactionStatus.CANCELED; // Can be handled using more generic message & errors.
        }

        TransactionType txnType  = txnTypeRepository.findById(txn.getOperationTypeId()).orElseThrow(null);  //Can be cached in memory with TTL.
        if(!txnType.isPosType() && account.get().getBalance()<txn.getAmount()){
            return TransactionStatus.CANCELED; //Can return status code with message.
        }

        Transaction tt = Transaction.builder()
                            .amount(txn.getAmount())
                            .account(account.get())
                            .status(null)
                            .amount(txnType.isPosType()?txn.getAmount():-txn.getAmount())
                            .transactionType(txnType)
                            .status(TransactionStatus.DONE)
                            .build();
        
        if(accountRepository.updateBalance(txnType.isPosType()?txn.getAmount():-txn.getAmount(), account.get().getId(), account.get().getBalance())<1){// Save the updated account balance, here we should update the balance in cache, and in async way we should update the balance.
            // Rollback.
            return TransactionStatus.FAILED;
        }
            
        // Save transaction
        transactionRepository.save(tt);
        return TransactionStatus.DONE;
    }
}
