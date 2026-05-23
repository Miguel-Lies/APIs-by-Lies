package com.enterprise.bank_lies.repository;

import com.enterprise.bank_lies.entity.BalanceOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceOfUser,Integer> {
    Optional<BalanceOfUser> findByUserId(Integer userId);
}
