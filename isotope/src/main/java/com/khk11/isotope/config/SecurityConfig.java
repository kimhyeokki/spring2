package com.khk11.isotope.config;

import com.khk11.isotope.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // 여기에 시큐리티가 적용하는 사항들을 설정....
    private  final CustomUserDetailService customUserDetailService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //1. 특정 설정을 통해 어디를 막을지 정해야함
        //2. js파일이나, css 파일도 막히기 때문에 허용해주어야 함
        httpSecurity.authorizeHttpRequests((auth)-> auth
                .requestMatchers("/","/member/**","/css/**","/js/**","/error")
                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin((auth)->auth
                        .loginPage("/member/login")   //로그인 패이지 정함(get방식)
                        .usernameParameter("id")   // username(default 값)의 파라미터를 개발자 임의 용어로 정할 수 있음
                        .loginProcessingUrl("/member/loginProcess") //(post) //UserDetailService
                        .defaultSuccessUrl("/isotope/main")
                        .permitAll()
                )
                .logout((auth)->auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .rememberMe((auth)->auth
                        .rememberMeParameter("saveMe")
                        .key(UUID.randomUUID().toString())
                        .userDetailsService(customUserDetailService)
                        .tokenValiditySeconds(60*24)
                )
                .csrf((auth)->auth.disable());


        return httpSecurity.build();
    }
}
