/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/

package com.skyhawks.gateway.resources.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationResponse {
    private  String jwt;
    private String schoolCode;
    private String userId;
    private List<String> userTypes;
    private boolean active;
    private boolean newUser;
}
