package com.enterprise.bank_lies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "information_balance")
public class BalanceOfUser {

    @Id
    private Integer id;

    @Column(name = "balance",precision = 19,scale = 2)
    private BigDecimal balance;

    @Column(name = "investments",precision = 19,scale = 2)
    private BigDecimal investment;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private BankDataAccountUser user;
}
