package com.zxc.common.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
public class JwtUtil {


    public static Collection<? extends GrantedAuthority> convertStringList2GrantedAuthorityList(List<String> list) {
        Collection<GrantedAuthority> roles = list
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;
    }

}
