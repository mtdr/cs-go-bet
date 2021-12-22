package com.edu.cs.go.bet.server.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka.producer")
public class KafkaProducerConfigProperties {

    private String bootstrapAddress;

    private String topicName;
}
