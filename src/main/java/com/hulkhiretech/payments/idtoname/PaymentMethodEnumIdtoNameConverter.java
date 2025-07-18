package com.hulkhiretech.payments.idtoname;

import org.modelmapper.AbstractConverter;

import com.hulkhiretech.payments.constant.PaymentMethodEnum;

public class PaymentMethodEnumIdtoNameConverter extends AbstractConverter<Integer, String>  {
    @Override
     protected String convert(Integer source){
        return PaymentMethodEnum.fromId(source).getName();
     }
}
