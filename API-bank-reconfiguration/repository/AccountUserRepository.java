package com.enterprise.bank_lies.repository;

import com.enterprise.bank_lies.entity.BankDataAccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountUserRepository extends JpaRepository<BankDataAccountUser,Integer> {
    Optional<BankDataAccountUser> findByEmail(String email);
    Optional<BankDataAccountUser> findByNumberOfUser(String number);
}
