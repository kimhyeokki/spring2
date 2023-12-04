package com.khk11.isotope.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@RequiredArgsConstructor
@Slf4j
@Getter
public class CustomUserDetails implements UserDetails {

    private final MemberJoin loggedMember;

    /*public CustomUserDetails(MemberJoin loggedMember){
        this.loggedMember = loggedMember;
    }*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 등급 : 관리자, 일반
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return loggedMember.getUserrole();
            }
        });
        return collections;
    }

    @Override
    public String getPassword() {
        return loggedMember.getPassword();
    }

    @Override
    public String getUsername() {
        return loggedMember.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
