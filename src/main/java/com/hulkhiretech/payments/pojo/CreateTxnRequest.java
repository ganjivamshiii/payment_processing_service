package com.hulkhiretech.payments.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class CreateTxnRequest {
    // private int id;
    private int userId;
    private String paymentMethod;
    private String provider;
    private String paymentType;
    // private int txnStatusId;
    private BigDecimal amount;
    private String currency;
    private String merchantTransactionReference;
    // private String txnReference;
    // private String providerReference;
    // private String errorCode;
    // private String errorMessage;
    // private Timestamp creationDate;
    // private int retryCount;
}
