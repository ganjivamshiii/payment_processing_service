package com.hulkhiretech.payments.dao;


import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.entity.TransactionEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TransactionDaoImpl implements TransactionDao {

    private final NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Override
    public boolean createTransaction(TransactionEntity entity) {
       String sql = "INSERT INTO payments.Transaction (" +
                "userId, paymentMethodId, providerId, paymentTypeId, txnStatusId, " +
                "amount, currency, merchantTransactionReference, txnReference, providerReference, " +
                "errorCode, errorMessage, retryCount) " +
                "VALUES (:userId, :paymentMethodId, :providerId, :paymentTypeId, :txnStatusId, " +
                ":amount, :currency, :merchantTransactionReference, :txnReference, :providerReference, " +
                ":errorCode, :errorMessage, :retryCount)";

        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, paramSource);
        log.info("Rows affected by transaction creation: {}", rowsAffected);
        return rowsAffected == 1;
    }
    }