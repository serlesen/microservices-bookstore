package com.sergio.bookstore.service.mails.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
public class KafkaListenerService {

    @Bean
    public Consumer<KStream<Object, String>> listenMessages() {
        return input -> {
            input.foreach((key, value) -> {
                var msgParts = value.split(":");
                log.info("Will perform a {} with content {}", msgParts[0], msgParts[1]);
            });
        };
    }
}
