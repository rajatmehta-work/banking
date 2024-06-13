package com.banking.dto;

import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    
    Long accountId;

    Integer operationTypeId;

    @Positive(message = "Amount must be positive")
    Double amount;
}
