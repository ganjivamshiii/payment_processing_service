package com.hulkhiretech.payments.pojo;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class IntiateTxnRequest {
   private int userId;
    private String paymentMethod;
    private String provider;
    private String paymentType;
    // private int txnStatusId;
    private BigDecimal amount;
    private String currency;
    private String merchantTransactionReference;
}
