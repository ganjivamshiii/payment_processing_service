package com.hulkhiretech.payments.service.factorydesign;

import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.constant.TransactionStatusEnum;
import com.hulkhiretech.payments.service.impl.statushandler.CreatedStatusHandler;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionStatusFactory {
     
   private final CreatedStatusHandler createdStatusHandler;
   public final ApplicationContext applicationContext;

    public TransactionStatusHandler getTransactionStatusHandler(TransactionStatusEnum txnStatusEnum){
        switch(txnStatusEnum){
            case CREATED:
                  log.info("payment method is created :{}",txnStatusEnum);
                  return createdStatusHandler;
         case INITIATED:
                   log.info("intiated payment method created",txnStatusEnum);
                  break;
        default:
               log.info("");
    }
    return null;
}
}