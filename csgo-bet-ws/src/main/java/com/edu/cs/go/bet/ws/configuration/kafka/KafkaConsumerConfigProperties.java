package com.edu.cs.go.bet.ws.configuration.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka.consumer")
public class KafkaConsumerConfigProperties {

    private String bootstrapAddress;

    private String topicName;
    private String groupId;
}
