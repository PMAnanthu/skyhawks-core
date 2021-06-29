/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.dtos.school.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SchoolRequest {
    private String uuid;

    @NotNull(message = "School code should not be null")
    @NotEmpty(message = "School code should not be empty")
    private String schoolCode;
    @NotNull(message = "School name should not be null")
    @NotEmpty(message = "School name should not be empty")
    private String name;
    @NotNull(message = "Email name should not be null")
    @NotEmpty(message = "Email name should not be empty")
    private String email;
    private String webSite;
    private String address;
    private String education;
    @NotNull(message = "Manager should not be null")
    @NotEmpty(message = "Manager should not be empty")
    private String mangerId;
    private boolean active;
}
