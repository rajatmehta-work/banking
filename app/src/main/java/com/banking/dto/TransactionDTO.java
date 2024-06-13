package com.banking.dto;

import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    
    Long accountId;

    Integer operationTypeId;// add check of id should present in transaction_type table

    @Min(value = 1, message = "Amount should be positive")
    Double amount;
}
