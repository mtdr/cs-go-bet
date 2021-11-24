package com.edu.cs.go.bet.server.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsGoClientConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "dathost")
    public DatHostConfigurationProperties datHostConfigurationProperties() {
        return new DatHostConfigurationProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "dathost.auth")
    public AuthDatHostConfigurationProperties authDatHostConfigurationProperties() {
        return new AuthDatHostConfigurationProperties();
    }

}
