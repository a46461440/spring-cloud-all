package com.zxc.apigateway.jwtsecurity;

import com.zxc.common.utils.JwtUtil;
import com.zxc.user.domain.po.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要使用的{@link UserDetails}实现
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrinciple implements UserDetails {

    private String id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrinciple build(UserInfo userInfo, List<String> list) {
        UserPrinciple userPrinciple = new UserPrinciple();
        BeanUtils.copyProperties(userInfo, userPrinciple);
        userPrinciple.setAuthorities(JwtUtil.convertStringList2GrantedAuthorityList(list));
        return userPrinciple;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
