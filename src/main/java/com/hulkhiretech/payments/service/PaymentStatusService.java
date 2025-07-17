package com.hulkhiretech.payments.service;


import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constant.TransactionStatusEnum;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.service.factorydesign.TransactionStatusFactory;
import com.hulkhiretech.payments.service.impl.PaymentServiceImpl;
import com.hulkhiretech.payments.service.impl.statushandler.CreatedStatusHandler;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.hulkhiretech.payments.constant.*;
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentStatusService {

     private final TransactionStatusFactory statusFactory;

     public TransactionDTO updatePayment(TransactionDTO txnDTO) {
               log.info("The status handler:{}", txnDTO);
               int txnStatusId=txnDTO.getTxnStatusId();
               TransactionStatusEnum statusEnum=TransactionStatusEnum.fromId(txnStatusId);
               TransactionStatusHandler statusHandler=statusFactory.getTransactionStatusHandler(statusEnum);
              if(statusHandler==null){
                     log.info("Thed transaction_id contains null",statusHandler);
                     throw new RuntimeException();
              }
              txnDTO = statusHandler.handleTransactionStatus(txnDTO);
              return txnDTO;
         }
}
