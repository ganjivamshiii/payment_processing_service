package com.hulkhiretech.payments.service.impl;


import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.hulkhiretech.payments.service.PaymentStatusService;
import com.hulkhiretech.payments.service.interfaces.PaymentService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.constant.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ModelMapper modelMapper;
    private final PaymentStatusService paymentStatusService;

    @Override
    public CreateTxnResponse CreatePayment(CreateTxnRequest createTxnRequest) {
        log.info("Received payment request | createTxnRequest: {}", createTxnRequest);

        String txnReference = UUID.randomUUID().toString();
        int txnStatusId = 1;
        log.info("Generated txnReference: {}, txnStatusId: {}", txnReference, txnStatusId);

        TransactionDTO txnDTO = modelMapper.map(createTxnRequest, TransactionDTO.class);
        txnDTO.setTxnStatusId(txnStatusId);
        txnDTO.setTxnReference(txnReference);

        log.info("Passing DTO to PaymentStatusService | txnDTO: {}", txnDTO);
        txnDTO = paymentStatusService.updatePayment(txnDTO);

        CreateTxnResponse response = new CreateTxnResponse();
        response.setTxnReference(txnDTO.getTxnReference());
        response.setTxnStatusId(txnDTO.getTxnStatusId());

        log.info("Mapped txnDTO to CreateTxnResponse: {}", response);
        return response;
    }
}
