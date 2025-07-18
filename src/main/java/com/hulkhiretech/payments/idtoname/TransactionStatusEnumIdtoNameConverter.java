package com.hulkhiretech.payments.idtoname;


import org.modelmapper.AbstractConverter;
import com.hulkhiretech.payments.constant.TransactionStatusEnum;

public class TransactionStatusEnumIdtoNameConverter extends AbstractConverter<Integer,String >{
    @Override
    protected String convert(Integer source){
      return TransactionStatusEnum.fromId(source).getName();
    } 
}