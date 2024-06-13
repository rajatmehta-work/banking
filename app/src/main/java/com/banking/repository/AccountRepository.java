package com.banking.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = a.balance + :delta WHERE a.id = :id AND a.balance = :prevBalance")
    int updateBalance(@Param("delta") Double delta, @Param("id") Long id, @Param("prevBalance") Double prevBalance);

    Optional<Account> findByDocumentNumber(BigDecimal documentNumber);
}
