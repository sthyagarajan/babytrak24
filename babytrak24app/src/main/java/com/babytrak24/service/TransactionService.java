package com.babytrak24.service;


import org.springframework.data.jpa.repository.JpaRepository;

import com.babytrak24.model.Transaction;

public interface TransactionService extends JpaRepository<Transaction, Long> {

}
