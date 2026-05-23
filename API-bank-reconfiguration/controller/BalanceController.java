package com.enterprise.bank_lies.controller;

import com.enterprise.bank_lies.dto.BalanceDTO;
import com.enterprise.bank_lies.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    final
    BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Void> addBalance(
            @PathVariable Integer id,
            @RequestBody BalanceDTO balanceDTO){

        balanceService.addBalance(id,balanceDTO.getValue());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/invest/{id}")
    public ResponseEntity<Void> addInvestment(
            @PathVariable Integer id,
            @RequestBody BalanceDTO balanceDTO){
        balanceService.addInvestment(id,balanceDTO.getValue());
        return ResponseEntity.ok().build();
    }

    @PostMapping("withdraw/{id}")
    public ResponseEntity<Void> withdrawBalance(
            @PathVariable Integer id,
            @RequestBody BalanceDTO balanceDTO){
        balanceService.withdraw(id,balanceDTO.getValue());
        return ResponseEntity.ok().build();
    }
}
