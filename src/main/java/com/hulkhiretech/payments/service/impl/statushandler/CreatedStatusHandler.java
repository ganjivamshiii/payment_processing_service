package com.hulkhiretech.payments.service.impl.statushandler;
import org.springframework.ui.ModelMap;

import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.entity.TransactionEntity;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;     
import com.hulkhiretech.payments.dao.interfaces.TransactionDao;

@Service 
@Slf4j
@RequiredArgsConstructor
public class CreatedStatusHandler implements  TransactionStatusHandler{

       private final TransactionDao transactionDao;
       private final ModelMapper modelMapper;
  

@Override
public TransactionDTO handleTransactionStatus(TransactionDTO transactionDTO) {
        log.info("Handling transaction status for Created status");
        TransactionEntity entity=modelMapper.map(transactionDTO, TransactionEntity.class);      
        boolean isCreated=transactionDao.createTransaction(entity);
        log.info(" Transaction created:{}", isCreated);
        if (!isCreated) {
			log.error("Failed to create transaction in database");
			throw new RuntimeException("Transaction creation failed");
		}
		
		log.info("Transaction successfully created in database, transactionDTO:{}",
				transactionDTO);
        return transactionDTO;  
    
}}

