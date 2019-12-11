package com.codve.mybatis.model.auth;

import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.util.SimpleAuthorityDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author admin
 * @date 2019/12/9 10:31
 */
@Data
public class AuthUser implements UserDetails, CredentialsContainer {

    private Long id;

    private String name;

    private String password;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @JsonDeserialize(using = SimpleAuthorityDeserializer.class)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public static AuthUser newInstance(UserDO userDO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(userDO.getId());
        authUser.setName(userDO.getName());
        authUser.setPassword(userDO.getPassword());

        UserType userType = UserType.getUserType(userDO.getType());
        authUser.setAuthorities(Collections.singleton(
                UserType.createAuthority(userType))
        );
        return authUser;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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

    @Override
    public void eraseCredentials() {
        password = null;
    }


}
