package com.hulkhiretech.payments.service.impl.statushandler;

import com.hulkhiretech.payments.constant.TransactionStatusEnum;
import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.entity.TransactionEntity;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntiatedStatusHandler implements  TransactionStatusHandler{
    private final TransactionDao transactionDao;
    private final ModelMapper modelMapper;
    @Override
   public TransactionDTO handleTransactionStatus(TransactionDTO transactionDTO) {
        TransactionEntity entity = modelMapper.map(transactionDTO, TransactionEntity.class);
        entity.setTxnStatusId(TransactionStatusEnum.INITIATED.getId());
        transactionDao.updateTransaction(entity);
        return transactionDTO;
    }
}
