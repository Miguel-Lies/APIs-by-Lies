package com.enterprise.bank_lies.service;

import com.enterprise.bank_lies.entity.BalanceOfUser;
import com.enterprise.bank_lies.entity.BankDataAccountUser;
import com.enterprise.bank_lies.entity.TransferOfUser;
import com.enterprise.bank_lies.exceptions.InsufficientBalanceException;
import com.enterprise.bank_lies.exceptions.NotFoundUserException;
import com.enterprise.bank_lies.repository.AccountUserRepository;
import com.enterprise.bank_lies.repository.BalanceRepository;
import com.enterprise.bank_lies.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class TransferService {

    @Autowired
    AccountUserRepository accountUserRepository;

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Transactional
    public void transferByEmail(Integer senderId, String receiverEmail, BigDecimal amount){
        BankDataAccountUser sender=accountUserRepository.findById(senderId)
                .orElseThrow(()
                -> new NotFoundUserException("Not found sender, Please try again."));

        BankDataAccountUser receiver=accountUserRepository.findByEmail(receiverEmail)
                .orElseThrow(()
                -> new NotFoundUserException("Not found receiver, please try again."));

        BalanceOfUser senderBalance = balanceRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Error! try again."));

        BalanceOfUser receiverBalance = balanceRepository.findById(receiver.getId())
                .orElseThrow(() -> new RuntimeException("Error! try again."));

        if (senderBalance.getBalance().compareTo(amount)<0){
        throw new InsufficientBalanceException("Insufficient balance.");
        }
        senderBalance.setBalance(senderBalance.getBalance().subtract(amount));
        receiverBalance.setBalance(receiverBalance.getBalance().add(amount));

        balanceRepository.save(senderBalance);
        balanceRepository.save(receiverBalance);

        TransferOfUser history = TransferOfUser.builder()
                .sender(sender)
                .receiver(receiver)
                .value(amount)
                .time(LocalDateTime.now())
                .build();
        transferRepository.save(history);
    }
}
