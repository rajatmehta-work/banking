package com.banking.dto;

import java.math.BigDecimal;
import com.banking.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {
    private Long accountId;
    private BigDecimal documentNo;

    public static AccountDTO fromAccount(Account account){
        return AccountDTO.builder().documentNo(account.getDocumentNo()).accountId(account.getId()).build();
    }
}
