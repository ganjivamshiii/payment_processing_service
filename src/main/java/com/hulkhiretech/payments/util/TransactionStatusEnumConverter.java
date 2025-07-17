package com.hulkhiretech.payments.util;

import com.hulkhiretech.payments.constant.TransactionStatusEnum;
import org.modelmapper.AbstractConverter;
public class TransactionStatusEnumConverter extends AbstractConverter<String, Integer> {
    @Override
    protected Integer convert(String source) {
        return TransactionStatusEnum.fromName(source).getId();
    }
}