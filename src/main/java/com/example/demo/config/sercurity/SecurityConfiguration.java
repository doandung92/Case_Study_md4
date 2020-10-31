package com.example.demo.config.sercurity;

import com.example.demo.service.security.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//@EnableSpringDataWebSupport
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SuccessHandle successHandle;

    @Autowired
    private ISecurityService securityService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService((UserDetailsService) securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/profile/**").access("hasRole('USER')")
                .antMatchers("/homepage/**").access("hasAnyRole('USER','ADMIN')")
                .antMatchers("admin/**").access("hasRole('ADMIN')")
                .and().formLogin().loginPage("/login").successHandler(successHandle)
                .and().exceptionHandling().accessDeniedPage("/notAuthor")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }
}
