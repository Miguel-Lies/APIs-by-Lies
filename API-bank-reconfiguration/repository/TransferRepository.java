package com.enterprise.bank_lies.repository;

import com.enterprise.bank_lies.entity.TransferOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferOfUser,Integer> {
}
