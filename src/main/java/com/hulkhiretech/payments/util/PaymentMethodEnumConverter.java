package com.hulkhiretech.payments.util;

import com.hulkhiretech.payments.constant.PaymentMethodEnum;
import org.modelmapper.AbstractConverter;

public class PaymentMethodEnumConverter extends AbstractConverter<String, Integer> {
    @Override
    protected Integer convert(String source) {
        return PaymentMethodEnum.fromName(source).getId();
    }
}
