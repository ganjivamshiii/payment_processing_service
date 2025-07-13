package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.CreateTxnRequest;
public interface PaymentService {
    public String CreatePayment(CreateTxnRequest createTxnRequest);
}
