package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.api.dto.common.Game;
import com.edu.cs.go.bet.match.configuration.KafkaProducerConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<String, Game> kafkaTemplate;
    private final KafkaProducerConfigProperties kafkaProducerConfigProperties;

    @Override
    public void sendGame(Game msg) {
        var future = kafkaTemplate.send(kafkaProducerConfigProperties.getTopicName(), msg);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Game> result) {
                log.info("Sent message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[" + msg + "] due to : " + ex.getMessage());
            }
        });
    }
}
