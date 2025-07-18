package com.hulkhiretech.payments.idtoname;

import org.modelmapper.AbstractConverter;

import com.hulkhiretech.payments.constant.ProviderEnum;

public class ProviderIdtoNameConverter extends AbstractConverter<Integer, String> {
     @Override
    protected String convert(Integer source){
      return ProviderEnum.fromId(source).getName();
    }
}
