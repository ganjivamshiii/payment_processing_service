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
    log.info("Updating payment with transactionDTO: {}", txnDTO);

    String txnStatus = txnDTO.getTxnStatus();

    if (txnStatus == null) {
        log.error("Transaction status is null in TransactionDTO");
        throw new RuntimeException("Transaction status is null");
    }

    TransactionStatusEnum statusEnum = TransactionStatusEnum.fromName(txnStatus);

    if (statusEnum == null) {
        log.error("Invalid txnStatus value: {}", txnStatus);
        throw new RuntimeException("Invalid transaction status: " + txnStatus);
    }

    TransactionStatusHandler statusHandler = statusFactory.getTransactionStatusHandler(statusEnum);

    if (statusHandler == null) {
        log.error("No status handler found for statusEnum: {}", statusEnum);
        throw new RuntimeException("Handler is null for status: " + statusEnum);
    }

    log.info("Using status handler: {}", statusHandler.getClass().getSimpleName());

    txnDTO = statusHandler.handleTransactionStatus(txnDTO);
    return txnDTO;
}

}
