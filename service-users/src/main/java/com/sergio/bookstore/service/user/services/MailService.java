package com.sergio.bookstore.service.user.services;

import com.sergio.bookstore.service.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendUserWelcomeMail(UserDto userDto) {
        kafkaTemplate.send("mails", "user.creation: " + userDto.getLogin());
    }
}
