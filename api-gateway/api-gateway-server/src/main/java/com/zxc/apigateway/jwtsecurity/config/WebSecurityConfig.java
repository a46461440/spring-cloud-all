package com.zxc.apigateway.jwtsecurity.config;

import com.zxc.apigateway.jwtsecurity.JwtAccessProblemHandler;
import com.zxc.apigateway.jwtsecurity.filter.JwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private JwtAccessProblemHandler jwtAccessProblemHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public JwtAuthTokenFilter jwtAuthTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(this.userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
        .and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/user/user/login/**").permitAll()
                .antMatchers("/**").hasRole("admin2")
        .and()
            .exceptionHandling().authenticationEntryPoint(this.jwtAccessProblemHandler)
            .accessDeniedHandler(this.jwtAccessProblemHandler)
        .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(this.jwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
