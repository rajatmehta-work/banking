package com.banking.entity;
import javax.persistence.*;
import com.banking.enums.TransactionStatus;
import lombok.Builder;
import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    // @Column(name = "initiated_by", nullable = false)
    // private String initiatedBy; User id will come.

    @Column(name = "initiated_at", nullable = false)
    private LocalDateTime initiatedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;


    // @Enumerated(EnumType.STRING)
    // @Column(name = "transaction_mode", nullable = false)
    // private TransactionMode transactionMode;

    // Getters and setters
}
