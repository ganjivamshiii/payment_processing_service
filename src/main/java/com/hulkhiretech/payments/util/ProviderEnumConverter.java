package com.hulkhiretech.payments.util;

import com.hulkhiretech.payments.constant.ProviderEnum;
import org.modelmapper.AbstractConverter;
public class ProviderEnumConverter extends AbstractConverter<String, Integer> {
    @Override
    protected Integer convert(String source) {
        return ProviderEnum.fromName(source).getId();
    }
}
