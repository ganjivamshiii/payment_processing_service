package com.hulkhiretech.payments.service.impl;


import org.springframework.stereotype.Service;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.hulkhiretech.payments.pojo.CreateTxnRequest; 
import com.hulkhiretech.payments.dao.interfaces.TransactionDao;


@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
       public final TransactionDao transactionDao;
         @Override
         public String CreatePayment(CreateTxnRequest createTxnRequest) {
                log.info("PaymentServiceImpl initialized:{}", createTxnRequest);
                Boolean isCreated= transactionDao.createTransaction();
                log.info("Transaction created: {}", isCreated);
                return null;
         }
          
}
