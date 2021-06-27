/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.gateway.services;

import com.skyhawks.UserTypeUtil;
import com.skyhawks.Utils;
import com.skyhawks.gateway.model.LoginUser;
import com.skyhawks.gateway.repos.IUserRepo;
import com.skyhawks.gateway.resources.requests.CreateUserRequest;
import com.skyhawks.gateway.resources.responses.UserDetailsModel;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class UserService implements UserDetailsService {

    private final IUserRepo iUserRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String userName) {
        return new UserDetailsModel(findByUsername(userName));
    }

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
}
