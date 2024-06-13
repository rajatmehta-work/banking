package com.banking.entity;

import javax.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.banking.dto.AccountDTO;
import com.banking.enums.AccountStatus;
import com.banking.enums.KycStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder // we should use custom builder or setter, so that balance should be 0 initialy.
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
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
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "document_number", nullable = false)
    private BigDecimal documentNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::account_status_enum")
    private AccountStatus status;

    @Column(name = "balance", nullable = false)
    @Builder.Default
    private Double balance = 0.0;

    // @Column(name = "kyc", nullable = false)
    // private KycStatus accountType;    


    public static Account fromAccountDTO(AccountDTO accountDTO){
        return Account.builder()
                        .documentNumber(accountDTO.getDocumentNumber())
                        .status(accountDTO.getStatus()==null?AccountStatus.ACTIVATED:accountDTO.getStatus())
                        .build();
    }
}
