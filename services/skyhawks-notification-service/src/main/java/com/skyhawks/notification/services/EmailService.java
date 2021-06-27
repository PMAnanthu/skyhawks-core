/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.notification.services;

import com.skyhawks.dtos.mails.requests.SendCreateMailRequest;
import com.skyhawks.notification.config.ApplicationConstants;
import lombok.Data;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmailService {

    private static final String SKYHAWKS_LOGIN_DETAILS = "Skyhawks Login details.";
    private static final String MESSAGE = "Hi %s, \n\nThanks for register with Skyhawks.\nPlease find the login " +
            "details. \n\nUsername:\t%s \n\nPassword:\t%s";
    private final JavaMailSender mailSender;

    public void sendCreateUserMail(SendCreateMailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getTo());
        mailMessage.setFrom(ApplicationConstants.APP_EMAIL);
        mailMessage.setSubject(SKYHAWKS_LOGIN_DETAILS);
        mailMessage.setText(String.format(MESSAGE, request.getName(), request.getUserName(), request.getPassword()));
        mailSender.send(mailMessage);
    }
}
