package com.mingming.accountservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {
//    @Id
    // Để yên để fix lỗi :))
    private String id;
    private String email;
    private String currency;
    private Double balance;
    private Double reserved;
}
