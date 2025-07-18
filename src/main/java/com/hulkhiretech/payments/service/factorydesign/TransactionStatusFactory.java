package com.hulkhiretech.payments.service.factorydesign;

import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.hulkhiretech.payments.pojo.IntiateTxnRequest;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.constant.TransactionStatusEnum;
import com.hulkhiretech.payments.service.impl.statushandler.CreatedStatusHandler;
import com.hulkhiretech.payments.service.impl.statushandler.IntiatedStatusHandler;
import com.hulkhiretech.payments.service.impl.statushandler.PendingStatusHandler;
@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionStatusFactory {
     
   public final CreatedStatusHandler createdStatusHandler;
   public final ApplicationContext applicationContext;
   public final IntiatedStatusHandler intiatedStatusHandler;
   public final PendingStatusHandler pendingStatusHandler;

    public TransactionStatusHandler getTransactionStatusHandler(TransactionStatusEnum txnStatusEnum){
        switch(txnStatusEnum){
            case CREATED:
                  log.info("payment method is created :{}",createdStatusHandler);
                  return createdStatusHandler;
            case INITIATED:
                   log.info("intiated payment method created",txnStatusEnum);
                   return intiatedStatusHandler;
            case PENDING:
                return pendingStatusHandler;
        default:
               log.info("");
    }
    return null;
}
}