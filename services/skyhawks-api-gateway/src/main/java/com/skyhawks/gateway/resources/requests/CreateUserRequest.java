/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.gateway.resources.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreateUserRequest {
    @NotNull(message = "User name should not be null")
    @NotEmpty(message = "User name should not be empty")
    private String userName;


    @NotNull(message = "User Type should not be null")
    @NotEmpty(message = "User Type  should not be empty")
    private List<String> userType;

    @NotNull(message = "School code should not be null")
    @NotEmpty(message = "School code should not be empty")
    private String schoolCode;
}
