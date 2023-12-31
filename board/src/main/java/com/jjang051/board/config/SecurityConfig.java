package com.jjang051.board.config;

import com.jjang051.board.handler.UserLoginFailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationFailureHandler userLoginFailHandler;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth)->auth
                .requestMatchers("/","/member/**","/css/**","/js/**","/images/**","/mail/**")
                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin((auth)->auth.loginPage("/member/login")
                        .usernameParameter("id")
                        .loginProcessingUrl("/member/loginProcess")
                        .defaultSuccessUrl("/board/list")
                        .failureHandler(userLoginFailHandler)
                       // .failureUrl("/member/login?error=true")
                        .permitAll())
                .logout((auth)->auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/member/login")
                        .invalidateHttpSession(true))
                .csrf((auth)->auth.disable());
        return httpSecurity.build();
    }
}
