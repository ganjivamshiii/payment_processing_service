package com.hulkhiretech.payments.dao;


import org.springframework.stereotype.Repository;

import com.hulkhiretech.payments.dao.interfaces.TransactionDao;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TransactionDaoImpl implements TransactionDao {
     @Override
    public boolean createTransaction() {
        // Implementation of transaction creation logic
        return true;
}

}