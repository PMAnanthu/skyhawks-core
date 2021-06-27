/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.dtos.school.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolResponse {
    private String uuid;
    private String schoolCode;
    private String name;
    private String email;
    private String webSite;
    private String address;
    private String education;
    private String mangerId;
    private boolean active;
}
