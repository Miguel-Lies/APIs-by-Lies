package com.enterprise.bank_lies.repository;

import com.enterprise.bank_lies.entity.BalanceOfUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<BalanceOfUser,Integer> {
    Optional<BalanceOfUser> findByUserId(Integer userId);
}
