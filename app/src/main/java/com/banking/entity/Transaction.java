package com.banking.entity;
import javax.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.banking.enums.TransactionStatus;
import lombok.Builder;
import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "transactions")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    // @Column(name = "initiated_by", nullable = false)
    // private String initiatedBy; User id will come.

    @Column(name = "initiated_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime initiatedAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @ColumnTransformer(write = "?::transaction_status_enum")
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionType transactionType;


    // @Enumerated(EnumType.STRING)
    // @Column(name = "transaction_mode", nullable = false)
    // private TransactionMode transactionMode;
 
}
