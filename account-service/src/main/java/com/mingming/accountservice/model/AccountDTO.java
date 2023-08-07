package com.mingming.accountservice.model;

import com.mingming.accountservice.data.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String id;
    private String email;
    private String currency;
    private Double balance;
    private Double reserved;

    public static AccountDTO entityToDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO);
        return accountDTO;
    }

    public static Account dtoToEntity(AccountDTO accountDTO){
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        return account;
    }

}
