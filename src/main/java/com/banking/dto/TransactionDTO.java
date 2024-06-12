package com.banking.dto;

import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class TransactionDTO {
    
    Long AccountId;

    Integer OperationTypeId;

    @Positive(message = "Amount must be positive")
    Double Amount;
}
