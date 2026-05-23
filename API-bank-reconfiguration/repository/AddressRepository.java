package com.enterprise.bank_lies.repository;

import com.enterprise.bank_lies.entity.AddressOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressOfUser,Integer> {
}
