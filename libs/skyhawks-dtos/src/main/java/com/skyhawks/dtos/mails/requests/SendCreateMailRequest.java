/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/

package com.skyhawks.dtos.mails.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class SendCreateMailRequest {
    @NotNull(message = "To address should not be null")
    @NotEmpty(message = "To address should not be empty")
    @Email(message = "To address should be proper mail address")
    private String to;

    private String name;

    @NotNull(message = "Username should not be null")
    @NotEmpty(message = "Username should not be empty")
    private String userName;

    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    private String password;

}
