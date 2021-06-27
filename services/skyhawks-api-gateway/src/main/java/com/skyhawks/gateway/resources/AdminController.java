/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 27/06/21
*/
package com.skyhawks.gateway.resources;

import com.skyhawks.dtos.school.requests.SchoolRequest;
import com.skyhawks.dtos.school.responses.SchoolResponse;
import com.skyhawks.gateway.config.ApplicationConfiguration;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
public class AdminController {

    public static final String SCHOOL = "school";
    public static final String SCHOOLS = "schools";
    private final RestTemplate restTemplate;
    private final ApplicationConfiguration applicationConfiguration;

    @PostMapping(path = SCHOOL)
    public ResponseEntity<String> saveSchool(@Valid @RequestBody SchoolRequest request) {
        HttpEntity<SchoolRequest> httpMailRequest = new HttpEntity<>(request);
        return restTemplate.exchange(
                String.format("%s/%s", applicationConfiguration.getSchoolServiceUrl(), SCHOOL),
                HttpMethod.POST,
                httpMailRequest,
                String.class);
    }

    @PutMapping(path = SCHOOL)
    public ResponseEntity<String> updateSchool(@Valid @RequestBody SchoolRequest request) {
        HttpEntity<SchoolRequest> httpMailRequest = new HttpEntity<>(request);
        return restTemplate.exchange(
                String.format("%s/%s", applicationConfiguration.getSchoolServiceUrl(), SCHOOL),
                HttpMethod.PUT,
                httpMailRequest,
                String.class);
    }

    @GetMapping(path = SCHOOLS)
    public List<?> getAllSchool() {
        return restTemplate.getForObject(
                String.format("%s/%s", applicationConfiguration.getSchoolServiceUrl(), SCHOOLS),
                List.class);
    }

    @GetMapping(path = "/school/{schoolCode}")
    public SchoolResponse getSchool(@PathVariable String schoolCode) {
        return restTemplate.getForObject(
                String.format("%s/%s/%s", applicationConfiguration.getSchoolServiceUrl(), SCHOOL, schoolCode),
                SchoolResponse.class);
    }

    @GetMapping(path = "/school/set-status/{schoolCode}")
    public ResponseEntity<String> setStatus(@PathVariable String schoolCode, @RequestParam boolean status) {
        return restTemplate.getForEntity(
                String.format("%s/%s/%s?status=%s", applicationConfiguration.getSchoolServiceUrl(), "/school/set-status/", schoolCode, status),
                String.class);
    }
}
