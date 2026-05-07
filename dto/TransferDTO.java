package com.enterprise.bank_lies.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDTO {
    private String receiver;
    private BigDecimal value;
}
