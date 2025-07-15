package com.hulkhiretech.payments.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
    public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

        // Use STRICT matching to avoid fuzzy/ambiguous matches
        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(AccessLevel.PRIVATE);

        return modelMapper;
    }
}

