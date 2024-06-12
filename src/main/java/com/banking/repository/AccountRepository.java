package com.banking.repository;

import com.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("Update account set balance = balance + ? where id = ? and balance = ?")
    public Boolean updateBalance(Double delta, Long id, Double prevBalance);
}
