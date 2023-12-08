package com.jjang051.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GithubUserInfo implements SocialUserInfo {
    private final Map<String,Object> attribute;

    @Override
    public String getProvider() {
        return "github";
    }

    @Override
    public String getProviderId() {
        return getProvider()+"_"+attribute.get("id");
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public String getName() {
        return (String) attribute.get("login");
    }
}
