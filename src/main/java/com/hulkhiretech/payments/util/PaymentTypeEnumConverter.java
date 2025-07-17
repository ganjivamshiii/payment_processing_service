package com.hulkhiretech.payments.util;

import com.hulkhiretech.payments.constant.PaymentTypeEnum;
import org.modelmapper.AbstractConverter;
public class PaymentTypeEnumConverter extends AbstractConverter<String, Integer> {
    
    @Override
    protected Integer convert(String source) {
        return PaymentTypeEnum.fromName(source).getId();
    }
}
