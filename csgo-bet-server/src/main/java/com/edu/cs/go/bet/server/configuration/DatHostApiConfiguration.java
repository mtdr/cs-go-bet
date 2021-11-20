package com.edu.cs.go.bet.server.configuration;

import com.edu.cs.go.bet.dathost.client.ApiClient;
import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import com.edu.cs.go.bet.dathost.client.auth.HttpBasicAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class DatHostApiConfiguration {

    private final AuthDatHostConfigurationProperties authProperties;

    @Bean
    public ApiClient apiClient() {
        var auth = new HttpBasicAuth();
        auth.setUsername(authProperties.getUsername());
        auth.setPassword(authProperties.getPassword());
        var client = new ApiClient(Map.of("base", auth));
        client.setBasePath(client.getBasePath().replace("http", "https"));
        return client;
    }

    @Bean
    public DatHostApi datHostApi() {
        return new DatHostApi(apiClient());
    }
}
