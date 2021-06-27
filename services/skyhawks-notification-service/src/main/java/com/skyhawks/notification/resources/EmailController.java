/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.notification.resources;

import com.skyhawks.dtos.mails.requests.SendCreateMailRequest;
import com.skyhawks.notification.services.EmailService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Data
@RestController
public class EmailController {

    private final EmailService emailService;

    @GetMapping(path = "test")
    public String test(){
        return "Success";
    }

    @PostMapping(path = "/mail/send/create-user")
    public void sendCreateUserMail(@Valid @RequestBody SendCreateMailRequest request){
        emailService.sendCreateUserMail(request);
    }
}
