package com.orchestrator.orchestrator.configuration;

import com.orchestrator.orchestrator.utils.constants.UserRoleConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000/"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE","OPTIONS","PATCH"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        String[] publicUrls = {"/api/v1/authenticate", "/api/v1/user/register"};
        String[] adminUrls = {"api/v1/**"};
        String[] playerUrls = {"api/v1/trivia/opened-trivias"};

        http.cors().configurationSource(request -> corsConfiguration)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(publicUrls).permitAll()
                .antMatchers(adminUrls).hasAnyAuthority(UserRoleConstants.ADMIN.name())
                .antMatchers(playerUrls).hasAnyAuthority(UserRoleConstants.PLAYER.name())
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginFilter(publicUrls[0], authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(publicUrls), UsernamePasswordAuthenticationFilter.class);
    }
}
