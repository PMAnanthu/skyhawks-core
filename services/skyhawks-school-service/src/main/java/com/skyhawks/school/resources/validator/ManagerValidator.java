/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.school.resources.validator;

import com.skyhawks.school.config.ApplicationConfiguration;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Data
@Component
@EqualsAndHashCode(callSuper=false)
public class ManagerValidator {

    public static final String VALID = "/valid/";
    private final RestTemplate restTemplate;

    private final ApplicationConfiguration applicationConfiguration;

    public boolean isValid(String value) {
        Boolean response =
                restTemplate.getForObject(applicationConfiguration.getGatewayServiceUrl()+ VALID +value, Boolean.class);
        return response;
    }
}
