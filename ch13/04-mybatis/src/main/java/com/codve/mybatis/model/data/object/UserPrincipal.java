package com.codve.mybatis.model.data.object;

import com.codve.mybatis.model.auth.UserType;
import com.codve.mybatis.util.SimpleAuthorityDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author admin
 * @date 2019/12/9 10:31
 */
@Data
public class UserPrincipal implements UserDetails {

    private Long id;

    private String name;

    private String password;

    @JsonDeserialize(using = SimpleAuthorityDeserializer.class)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public static UserPrincipal newInstance(UserDO userDO) {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(userDO.getId());
        userPrincipal.setName(userDO.getName());
        userPrincipal.setPassword(userDO.getPassword());

        UserType userType = UserType.getUserType(userDO.getType());
        userPrincipal.setAuthorities(Collections.singleton(
                UserType.createAuthority(userType)));
        return userPrincipal;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
