package com.demo.patterns.email_service.controller;

import com.demo.patterns.email_service.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
public class Controller {

    private final EmailService emailService;

    @PostMapping("/send-email")
    public void sendEmail(@RequestParam("name") String name,
                          @RequestParam("email") @Email String recipient) throws MessagingException {
        log.info("Sending email from {} to {}", name, recipient);
        emailService.sendEmail(recipient, "Email from email-service", name);
    }
}
