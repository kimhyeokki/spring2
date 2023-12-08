package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Member02;
import com.jjang051.jpa.repository.MemberRepository;
import com.jjang051.jpa.social.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.UnknownContentTypeException;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("구글 로그인 하면 여기로 들어옴 여기서 작업하면 됨");
       // userRequest.getClientRegistration().getRegistrationId() ==naver or google 얻어올 수 있다.

       log.info("==={}",userRequest.getClientRegistration().getRegistrationId());
        OAuth2User oAuth2User = super.loadUser(userRequest); // 부모에서 받겠다
        log.info("oAuth2User.getAuthorities()==={}",oAuth2User.getAttributes());
        Map<String,Object> oAuth2UserInfo = (Map)oAuth2User.getAttributes();

        String provider = userRequest.getClientRegistration().getRegistrationId();
        SocialUserInfo socialUserInfo = null;
        Member02 returnMember= null;
       /* String email = null;
        String nickName = null;
        String userId = null;*/
        if(provider.equals("google")){
            socialUserInfo = new GoogleUserInfo(oAuth2UserInfo);
           /* email = (String) oAuth2UserInfo.get("email");
            nickName =(String) oAuth2UserInfo.get("name");
            userId = provider+"_"+(String) oAuth2UserInfo.get("sub");*/
        }else if(provider.equals("naver")){
            Map<String,Object> naverResponse = (Map)oAuth2UserInfo.get("response");
            socialUserInfo = new NaverUserInfo(naverResponse);
           /* Map<String,Object> naverResponse = (Map)oAuth2UserInfo.get("response");
            email = (String) naverResponse.get("email");
            nickName =(String) naverResponse.get("name");
            userId = provider+"_"+(String) naverResponse.get("id");*/
        } else if(provider.equals("kakao")){
            socialUserInfo = new KakaoUserInfo(oAuth2UserInfo);
            /*Map<String, Object> kakaoResponse = (Map)oAuth2UserInfo.get("properties");
            Map<String, Object> kakaoAccount = (Map)oAuth2UserInfo.get("kakao_account");
            email = (String) kakaoAccount.get("email");
            nickName = (String) kakaoResponse.get("nickname");
            userId = provider+"_"+(String) oAuth2UserInfo.get("id").toString();*/
        }else if(provider.equals("github")){
            socialUserInfo = new GithubUserInfo(oAuth2UserInfo);
        }
        String email = socialUserInfo.getEmail();
        String nickName = socialUserInfo.getName();
        String userId = socialUserInfo.getProviderId();
        String role = "USER_ROLE";
        String password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString());

        Optional<Member02> findId = memberRepository.findByUserId(userId);
        if(findId.isPresent()){
            returnMember= findId.get();
        }else {
            returnMember = Member02.builder()
                    .userId(userId)
                    .password(password)
                    .role(role)
                    .nickName(nickName)
                    .email(email)
                    .build();
            memberRepository.save(returnMember);  //오류는 계속 로그인하면 계속 저장이 되어서 무결성 제약에 걸린다.
        }

        return new CustomUserDetails(returnMember,oAuth2User.getAttributes());
    }


}
