package com.jjang051.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class NaverUserInfo implements SocialUserInfo{

    private final Map<String,Object> attribute;
    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return getProvider()+"_"+(String) attribute.get("id");
    }

    @Override
    public String getEmail() {
        return (String) attribute.get("email");
    }

    @Override
    public String getName() {
        return (String) attribute.get("nickname");
    }
}
