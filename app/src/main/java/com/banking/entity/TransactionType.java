package com.banking.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.banking.enums.TransactionTypeEnum;
import lombok.Builder;


@Entity
@Builder
@Table(name = "transaction_type")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", unique = true, nullable = false)
    private TransactionTypeEnum type;

    public Boolean isPosType(){
        return this.type==TransactionTypeEnum.CREDIT_VOUCHER;
    }

}
