package com.banking.entity;

import javax.persistence.*;
import com.banking.dto.AccountDTO;
import com.banking.enums.AccountStatus;
import com.banking.enums.KycStatus;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder // we should use custom builder or setter, so that balance should be 0 initialy.
@Getter
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "masked_id", unique = true, nullable = false)
    // private String maskedId;

    // @Enumerated(EnumType.STRING)
    // @Column(name = "account_type", nullable = false)
    // private AccountType accountType;

    // @Column(name = "password", nullable = false)
    // private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "document_no", nullable = false)
    private BigDecimal documentNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @Column(name = "balance", nullable = false)
    private Double balance;

    // @Column(name = "kyc", nullable = false)
    // private KycStatus accountType;    

    // Getters and setters

    public static Account fromAccountDTO(AccountDTO accountDTO){
        return Account.builder().documentNo(accountDTO.getDocumentNo()).build();
    }
}
