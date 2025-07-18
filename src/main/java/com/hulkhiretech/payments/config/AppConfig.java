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
import com.hulkhiretech.payments.util.*;
import com.hulkhiretech.payments.idtoname.*;

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

        // Entity -> DTO converters
        Converter<Integer, String> paymentMethodIdToNameConverter = new PaymentMethodEnumIdtoNameConverter();
        Converter<Integer, String> paymentTypeIdToNameConverter = new PaymentTypeIdtoNameConverter();
        Converter<Integer, String> providerIdToNameConverter = new ProviderIdtoNameConverter();
        Converter<Integer, String> transactionStatusIdToNameConverter = new TransactionStatusEnumIdtoNameConverter();

        // DTO to Entity mapping
        modelMapper.addMappings(new PropertyMap<TransactionDTO, TransactionEntity>() {
            @Override
            protected void configure() {
                using(paymentMethodEnumConverter).map(source.getPaymentMethod(), destination.getPaymentMethodId());
                using(paymentTypeEnumConverter).map(source.getPaymentType(), destination.getPaymentTypeId());
                using(providerEnumConverter).map(source.getProvider(), destination.getProviderId());
                using(transactionStatusEnumConverter).map(source.getTxnStatus(), destination.getTxnStatusId());
            }
        });

        // Entity to DTO mapping
        modelMapper.addMappings(new PropertyMap<TransactionEntity, TransactionDTO>() {
            @Override
            protected void configure() {
                using(paymentMethodIdToNameConverter).map(source.getPaymentMethodId(), destination.getPaymentMethod());
                using(paymentTypeIdToNameConverter).map(source.getPaymentTypeId(), destination.getPaymentType());
                using(providerIdToNameConverter).map(source.getProviderId(), destination.getProvider());
                using(transactionStatusIdToNameConverter).map(source.getTxnStatusId(), destination.getTxnStatus());
            }
        });

        return modelMapper;
    }
}
