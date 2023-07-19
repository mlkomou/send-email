package com.miriblon.sendemail.controller;

import com.miriblon.sendemail.service.SendEmailService;
import org.springframework.web.bind.annotation.*;
import peyload.EmailPayload;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final SendEmailService emailService;

    public EmailController(SendEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailPayload emailPayload) {
        emailPayload.setMessage(emailService.mailTemplatePassword(emailPayload));
         emailService.sendEmail(emailPayload);
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello, welcome to jenkins ci/cd app automatic deploy";
    }
}
