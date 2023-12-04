package com.jjang051.board.service;

import com.jjang051.board.dao.MemberDao;
import com.jjang051.board.dto.CustomUserDatails;
import com.jjang051.board.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDatailService implements UserDetailsService {
    private final MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JoinDto loggedMember = memberDao.loginMember(username);
        if(loggedMember==null){
            throw new UsernameNotFoundException("아이디 패스워드 확인해주세요");
        }
            return new CustomUserDatails(loggedMember);

    }
}
