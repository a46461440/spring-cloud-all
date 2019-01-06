package com.zxc.apigateway.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2019/1/6
 */
@Data
public class JwtUserDetails implements UserDetails {

    private String subject;
    private String id;
    private String jwtToken;
    private List<? extends GrantedAuthority> grantedAuthorities;

    public JwtUserDetails(String subject, String id, String jwtToken, List<GrantedAuthority> grantedAuthorities) {
        this.subject = subject;
        this.id = id;
        this.jwtToken = jwtToken;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.subject;
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
