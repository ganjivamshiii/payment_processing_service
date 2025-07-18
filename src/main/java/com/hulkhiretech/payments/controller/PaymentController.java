package com.hulkhiretech.payments.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hulkhiretech.payments.dao.TransactionDaoImpl;
import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;

import org.springframework.web.bind.annotation.RequestBody;
import com.hulkhiretech.payments.service.impl.PaymentServiceImpl;
import com.hulkhiretech.payments.service.impl.statushandler.IntiatedStatusHandler;
import com.hulkhiretech.payments.pojo.IntiateTxnRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payments")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentServiceImpl paymentServiceImpl;
    @PostMapping
    public CreateTxnResponse CreatePayment(@RequestBody CreateTxnRequest createTxnRequest) {
        log.info("PaymentController initialized:{}", createTxnRequest);
        CreateTxnResponse response=paymentServiceImpl.CreatePayment(createTxnRequest);
        return response;
    }
    @PostMapping("/{txnReference}/intiate")
    public String GetPaymentStatus(@PathVariable String txnReference, @RequestBody IntiateTxnRequest intiateTxnRequest) {
               
         String create= paymentServiceImpl.intiatePayment(txnReference,intiateTxnRequest);
         return create;
    }
    
}
