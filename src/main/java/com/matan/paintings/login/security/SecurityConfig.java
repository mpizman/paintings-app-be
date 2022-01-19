package com.matan.paintings.login.security;

import com.matan.paintings.login.security.filter.CustomAuthenticationFilter;
import com.matan.paintings.login.security.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/painting/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/painting").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.GET,"/api/paintings").permitAll()
                .antMatchers(HttpMethod.PATCH,"/api/paintings/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.DELETE,"/api/paintings/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST,"/api/user").permitAll()
                .antMatchers("/api/user").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/users").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/role").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/role/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
