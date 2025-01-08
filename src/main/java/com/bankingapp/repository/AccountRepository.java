package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.entities.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
