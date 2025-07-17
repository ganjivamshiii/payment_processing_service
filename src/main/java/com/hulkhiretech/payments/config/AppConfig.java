package com.hulkhiretech.payments.config;
import org.modelmapper.Converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.entity.TransactionEntity;
import com.hulkhiretech.payments.util.PaymentMethodEnumConverter;
// import com.hulkhiretech.payments.util.*;
import com.hulkhiretech.payments.util.PaymentTypeEnumConverter;
import com.hulkhiretech.payments.util.ProviderEnumConverter;
import com.hulkhiretech.payments.util.TransactionStatusEnumConverter;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(AccessLevel.PRIVATE);

        // Create and register converters
        Converter<String, Integer> paymentMethodEnumConverter = new PaymentMethodEnumConverter();
        Converter<String, Integer> paymentTypeEnumConverter = new PaymentTypeEnumConverter();
        Converter<String, Integer> providerEnumConverter = new ProviderEnumConverter();
        Converter<String, Integer> transactionStatusEnumConverter = new TransactionStatusEnumConverter();

        // Add custom mappings
        modelMapper.addMappings(new PropertyMap<TransactionDTO, TransactionEntity>() {
            @Override
            protected void configure() {
                using(paymentMethodEnumConverter).map(source.getPaymentMethod(), destination.getPaymentMethodId());
                using(paymentTypeEnumConverter).map(source.getPaymentType(), destination.getPaymentTypeId());
                using(providerEnumConverter).map(source.getProvider(), destination.getProviderId());
                using(transactionStatusEnumConverter).map(source.getTxnStatus(), destination.getTxnStatusId());
            }
        });

        return modelMapper;
    }
}
