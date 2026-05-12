package com.enterprise.bank_lies.repository;

import com.enterprise.bank_lies.entity.TransferOfUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferOfUser,Integer> {
}
