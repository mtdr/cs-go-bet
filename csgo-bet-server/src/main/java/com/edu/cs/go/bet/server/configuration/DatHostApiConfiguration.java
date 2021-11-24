package com.edu.cs.go.bet.server.configuration;

import com.edu.cs.go.bet.dathost.client.ApiClient;
import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@RequiredArgsConstructor
@Configuration
public class DatHostApiConfiguration {

    private final AuthDatHostConfigurationProperties authProperties;

    @Bean
    public ApiClient apiClient() {
        var client = new ApiClient();
        client.setBasePath(client.getBasePath().replace("http", "https"));
        client.addDefaultHeader("Authorization", "Basic " + HttpHeaders.encodeBasicAuth(authProperties.getUsername(), authProperties.getPassword(), null));
        return client;
    }

    @Bean
    public DatHostApi datHostApi() {
        return new DatHostApi(apiClient());
    }
}
