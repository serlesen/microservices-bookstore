package com.sergio.bookstore.service.user.controllers;

import com.sergio.bookstore.service.user.dto.CredentialsDto;
import com.sergio.bookstore.service.user.dto.UserCreationDto;
import com.sergio.bookstore.service.user.dto.UserDto;
import com.sergio.bookstore.service.user.services.MailService;
import com.sergio.bookstore.service.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserControllers {

    private final UserService userService;
    private final MailService mailService;

    @PostMapping("/signIn")
    public ResponseEntity<UserDto> signIn(@RequestBody CredentialsDto credentialsDto) {
        log.info("Trying to login {}", credentialsDto.getLogin());
        return ResponseEntity.ok(userService.signIn(credentialsDto));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<UserDto> signIn(@RequestParam String token) {
        log.info("Trying to validate token {}", token);
        return ResponseEntity.ok(userService.validateToken(token));
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody UserCreationDto userCreationDto) {
        log.info("Creating new user {}", userCreationDto.getLogin());
        UserDto user = userService.signUp(userCreationDto);
        mailService.sendUserWelcomeMail(user);
        return ResponseEntity.ok(user);
    }
}
