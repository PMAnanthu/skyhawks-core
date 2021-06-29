/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.authentication.resources;

import com.skyhawks.authentication.config.ApplicationConfiguration;
import com.skyhawks.authentication.config.jwt.JWTUtil;
import com.skyhawks.authentication.model.LoginUser;
import com.skyhawks.authentication.resources.dto.AuthenticationResponse;
import com.skyhawks.authentication.resources.requests.AuthenticationRequest;
import com.skyhawks.authentication.resources.requests.CreateUserRequest;
import com.skyhawks.authentication.services.UserService;
import com.skyhawks.dtos.mails.requests.SendCreateMailRequest;
import com.skyhawks.dtos.resonses.UserDto;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Data
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    public static final String MAIL_SEND_CREATE_USER = "/mail/send/create-user";
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest request) {
        final LoginUser userDetails = userService.findByUsername(request.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(AuthenticationResponse
                .builder()
                .jwt(jwt)
                .newUser(userDetails.getNewUser())
                .active(userDetails.getEnabled())
                .userId(userDetails.getUuid().toString())
                .userName(userDetails.getUserName()).build());
    }

    @GetMapping("/validateToken")
    public ResponseEntity<UserDto> signIn(@RequestParam String token) {
        return ResponseEntity.ok(userService.validateToken(token));
    }
}
