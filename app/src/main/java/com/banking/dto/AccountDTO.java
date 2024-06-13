package com.banking.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.banking.entity.Account;
import com.banking.enums.AccountStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @ApiModelProperty(hidden = true)
    private Long accountId;

    @Min(value = 1, message = "Document number should be positive")
    private BigDecimal documentNumber;

    @ApiModelProperty(hidden = true)
    private AccountStatus status;

    public static AccountDTO fromAccount(Account account){
        return AccountDTO.builder().documentNumber(account.getDocumentNumber()).status(account.getStatus()).accountId(account.getId()).build();
    }
}
