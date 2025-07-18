package com.hulkhiretech.payments.dao;


import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
      @Override
    public TransactionEntity getTransactionByReference(String txnReference) {
        String sql = "SELECT * FROM payments.Transaction WHERE txnReference = :txnReference";

        Map<String, Object> params = new HashMap<>();
        params.put("txnReference", txnReference);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params,
                new BeanPropertyRowMapper<>(TransactionEntity.class));
        } catch (EmptyResultDataAccessException e) {
            return null; // not found
        }
    }

    @Override
    public boolean updateTransaction(TransactionEntity entity) {
        String sql = "UPDATE payments.Transaction SET " +
                "userId = :userId, paymentMethodId = :paymentMethodId, providerId = :providerId, " +
                "paymentTypeId = :paymentTypeId, txnStatusId = :txnStatusId, amount = :amount, " +
                "currency = :currency, merchantTransactionReference = :merchantTransactionReference, " +
                "providerReference = :providerReference, errorCode = :errorCode, errorMessage = :errorMessage, " +
                "retryCount = :retryCount WHERE txnReference = :txnReference";

        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, paramSource);
        return rowsAffected == 1;
    }
}