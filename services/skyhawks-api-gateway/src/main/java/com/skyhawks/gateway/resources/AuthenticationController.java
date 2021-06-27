/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.gateway.resources;

import com.skyhawks.UserTypeUtil;
import com.skyhawks.dtos.UserType;
import com.skyhawks.dtos.mails.requests.SendCreateMailRequest;
import com.skyhawks.gateway.config.ApplicationConfiguration;
import com.skyhawks.gateway.config.jwt.JWTUtil;
import com.skyhawks.gateway.model.LoginUser;
import com.skyhawks.gateway.resources.requests.AuthenticationRequest;
import com.skyhawks.gateway.resources.requests.CreateUserRequest;
import com.skyhawks.gateway.resources.responses.AuthenticationResponse;
import com.skyhawks.gateway.services.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Data
@RestController
public class AuthenticationController {

    public static final String MAIL_SEND_CREATE_USER = "/mail/send/create-user";
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JWTUtil jwtUtil;

    private final RestTemplate restTemplate;

    private final ApplicationConfiguration applicationConfiguration;

    @GetMapping(path = "/test")
    public String test(){
        return "Success";
    }

    @GetMapping(path = "/authored/test")
    public String testAfterLogin(){
        return "Success";
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        LoginUser loginUser = userService.findByUsername(request.getUserName());
        authenticationResponse.setJwt(jwtUtil.generateToken(loginUser));
        authenticationResponse.setUserTypes(UserTypeUtil.getUserType(loginUser.getUserType())
                .stream().map(UserType::toString).collect(Collectors.toList()));
        authenticationResponse.setSchoolCode(loginUser.getSchoolCode());
        authenticationResponse.setUserId(loginUser.getUuid().toString());
        authenticationResponse.setNewUser(loginUser.getNewUser());
        authenticationResponse.setActive(loginUser.getEnabled());
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request){
        LoginUser loginUser= userService.saveUser(request);
        if(loginUser!=null && loginUser.getUuid()!=null){
            SendCreateMailRequest mailRequest = new SendCreateMailRequest();
            mailRequest.setName("");
            mailRequest.setTo(loginUser.getUserName());
            mailRequest.setPassword(loginUser.getPassword());
            mailRequest.setUserName(loginUser.getUserName());
            HttpEntity<SendCreateMailRequest> httpMailRequest = new HttpEntity<>(mailRequest);
            ResponseEntity<String> response =
                    restTemplate.exchange(applicationConfiguration.getNotificationServiceUrl()+ MAIL_SEND_CREATE_USER, HttpMethod.POST,
                    httpMailRequest,
                    String.class);
        }
        return ResponseEntity.ok("User created successfully");
    }

    @Hidden
    @PostMapping(path = "/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody String e){
        return null;
    }

    @Hidden
    @GetMapping(path = "/valid/{userId}")
    public boolean validate(@PathVariable String userId){
        return userService.isValidUser(userId);
    }


}
