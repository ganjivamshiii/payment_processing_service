package com.hulkhiretech.payments.dao.interfaces;

import org.springframework.stereotype.Repository;
import com.hulkhiretech.payments.entity.TransactionEntity;

public interface TransactionDao {
    public boolean createTransaction(TransactionEntity entity);
    TransactionEntity getTransactionByReference(String txnReference);

    // Update an existing transaction in the DB
    boolean updateTransaction(TransactionEntity entity);
}
