package com.jjang051.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoUserInfo implements SocialUserInfo{
    private final Map<String,Object> attribute;
    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return getProvider()+"_"+(String) attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakoAccount = (Map)attribute.get("kakao_account");
        return (String) kakoAccount.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> kakaoResponse = (Map) attribute.get("properties");
        return (String) kakaoResponse.get("nickname");
    }
}
