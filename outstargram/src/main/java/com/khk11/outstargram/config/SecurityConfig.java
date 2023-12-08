package com.khk11.outstargram.config;


import com.khk11.outstargram.Service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2DetailsService oAuth2DetailsService;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth->auth
                .requestMatchers("/","/auth/login","/css/**","/js/**","/images/**")
                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin(form->form
                        .loginPage("/auth/login")
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(out->out
                        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true))
                .oauth2Login(oauth2->oauth2
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint(userInfo->userInfo
                                .userService(oAuth2DetailsService)
                        )
                )

                .csrf(csrf->csrf.disable());

        return httpSecurity.build();
    }
}
