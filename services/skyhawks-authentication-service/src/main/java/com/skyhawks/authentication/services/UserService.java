/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.authentication.services;

import com.skyhawks.UserTypeUtil;
import com.skyhawks.Utils;
import com.skyhawks.authentication.config.jwt.JWTUtil;
import com.skyhawks.authentication.model.LoginUser;
import com.skyhawks.authentication.repos.IUserRepo;
import com.skyhawks.authentication.resources.requests.CreateUserRequest;
import com.skyhawks.dtos.resonses.UserDto;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Service
public class UserService {

    private final IUserRepo iUserRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;



    public LoginUser findByUsername(String userName) {
        return iUserRepo.findByUserName(userName);
    }


    public LoginUser saveUser(CreateUserRequest request) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(request.getUserName());
        String passed = new String(Utils.randomChar(8));
        loginUser.setPassword(passwordEncoder.encode(passed));
        loginUser.setUserType(UserTypeUtil.getValueFromString(request.getUserType()));
        loginUser.setSchoolCode(request.getSchoolCode());
        loginUser.setNewUser(true);
        loginUser.setAccountNonExpired(true);
        loginUser.setAccountNonLocked(true);
        loginUser.setCredentialsNonExpired(true);
        loginUser.setEnabled(true);
        loginUser =iUserRepo.save(loginUser);
        loginUser.setPassword(passed);
        return loginUser;
    }

    public boolean isValidUser(String userId) {
       return iUserRepo.findById(UUID.fromString(userId)).isPresent();
    }

    public UserDto validateToken(String token) {
        String userName=jwtUtil.extractUserName(token);
        LoginUser user = findByUsername(userName);
        if(user!=null){
            if(jwtUtil.validateToken(token,user)){
                UserDto userDto=new UserDto();
                userDto.setId(user.getUuid().toString());
                userDto.setLogin(user.getUserName());
                userDto.setToken(token);
                return userDto;
            }
        }
        return null;
    }
}
