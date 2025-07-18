package com.hulkhiretech.payments.service.impl;


import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.hulkhiretech.payments.service.PaymentStatusService;
import com.hulkhiretech.payments.service.interfaces.PaymentService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.entity.TransactionEntity;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.pojo.IntiateTxnRequest;
import com.hulkhiretech.payments.constant.*;
import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ModelMapper modelMapper;
    private final PaymentStatusService paymentStatusService;
    private final TransactionDao transactionDao;


    @Override
    public CreateTxnResponse CreatePayment(CreateTxnRequest createTxnRequest) {
        log.info("Received payment request | createTxnRequest: {}", createTxnRequest);
        
        String txnReference = UUID.randomUUID().toString();
        /*---------------------------------------------------------------- */
        String txnStatus = TransactionStatusEnum.fromName("CREATED").getName();
        log.info("Generated txnReference: {}, txnStatusId: {}", txnReference, txnStatus);


        TransactionDTO txnDTO = modelMapper.map(createTxnRequest, TransactionDTO.class);
        txnDTO.setTxnStatus(txnStatus);
        txnDTO.setTxnReference(txnReference);
        log.info("Passing DTO to PaymentStatusService | txnDTO: {}", txnDTO);
        txnDTO = paymentStatusService.updatePayment(txnDTO);

        CreateTxnResponse response = new CreateTxnResponse();
        response.setTxnReference(txnDTO.getTxnReference());
        response.setTxnStatus(txnDTO.getTxnStatus());

        log.info("Mapped txnDTO to CreateTxnResponse: {}", response);
        return response;
    }

    @Override
    public String intiatePayment(String txnReference,IntiateTxnRequest intiateTxnRequest){
        
            TransactionEntity txnEntity = transactionDao.getTransactionByReference(txnReference);
    if (txnEntity == null) {
        throw new RuntimeException("Transaction not found for reference: " + txnReference);
    }

    // 2. Convert Entity to DTO
    TransactionDTO txnDTO = modelMapper.map(txnEntity, TransactionDTO.class);

    // 3. Set initial status to INITIATED and update via statusService
    txnDTO.setTxnStatus(TransactionStatusEnum.INITIATED.getName());
    txnDTO = paymentStatusService.updatePayment(txnDTO); // This will internally use InitiatedStatusHandler

    // 5. After successful call, update to PENDING
    txnDTO.setTxnStatus(TransactionStatusEnum.PENDING.getName());
    txnDTO = paymentStatusService.updatePayment(txnDTO); // This will internally use PendingStatusHandler
    //private String txnStatus;
    return "Payment initiated successfully for: " + txnDTO.getTxnStatus();

    }
}
