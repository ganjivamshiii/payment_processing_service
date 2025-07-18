package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.pojo.IntiateTxnRequest;

public interface PaymentService {
    public CreateTxnResponse CreatePayment(CreateTxnRequest createTxnRequest);
    public String  intiatePayment(String txnReference,IntiateTxnRequest intiateTxnRequest);
}
