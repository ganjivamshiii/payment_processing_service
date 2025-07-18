package com.hulkhiretech.payments.idtoname;

import com.hulkhiretech.payments.constant.PaymentTypeEnum;
import org.modelmapper.AbstractConverter;

public class PaymentTypeIdtoNameConverter extends AbstractConverter<Integer, String> {
    @Override
    protected String convert(Integer source){
      return PaymentTypeEnum.fromId(source).getName();
    }
}
