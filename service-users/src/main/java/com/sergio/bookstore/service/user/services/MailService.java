package com.sergio.bookstore.service.user.services;

import com.sergio.bookstore.service.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @NewSpan("send-email")
    public void sendUserWelcomeMail(UserDto userDto) {
        log.info("Sending a creation email for {}", userDto.getLogin());
        kafkaTemplate.send("mails", "user.creation: " + userDto.getLogin());
    }
}
