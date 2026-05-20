package com.enterprise.bank_lies.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceDTO {
    private BigDecimal balance;
    private BigDecimal investments;
    private BigDecimal value;
}
