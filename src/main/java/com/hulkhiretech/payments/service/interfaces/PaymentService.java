package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
public interface PaymentService {
    public CreateTxnResponse CreatePayment(CreateTxnRequest createTxnRequest);
}
