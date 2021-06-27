package com.skyhawks.gateway.resources.responses;

import com.skyhawks.UserTypeUtil;
import com.skyhawks.dtos.UserType;
import com.skyhawks.gateway.model.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsModel implements UserDetails {
    private final List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
    private final String password;
    private final String userName;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final boolean newUser;

    public UserDetailsModel(LoginUser user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        List<UserType> userTypes = UserTypeUtil.getUserType(user.getUserType());
        userTypes.forEach(userType -> {
            this.grantedAuthorities.add(new UserTypeObject(userType.toString()));
        });
        this.accountNonExpired = user.getAccountNonExpired();
        this.credentialsNonExpired = user.getCredentialsNonExpired();
        this.accountNonLocked = user.getAccountNonLocked();
        this.newUser=user.getNewUser();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    class UserTypeObject implements GrantedAuthority{

        private final String value;

        UserTypeObject(String value) {
            this.value = value;
        }

        @Override
        public String getAuthority() {
            return this.value;
        }
    }

}