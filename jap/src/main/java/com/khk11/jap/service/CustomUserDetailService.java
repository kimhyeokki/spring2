package com.khk11.jap.service;

import com.khk11.jap.dto.CustomUserDetails;
import com.khk11.jap.entity.Member02;
import com.khk11.jap.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
      Optional<Member02>  loggedMember = memberRepository.findByUserId(userId);
     if(loggedMember.isPresent()){
        return new CustomUserDetails(loggedMember.get());
     }
      throw new UsernameNotFoundException("아이디 패스워드 확인해주세요");
    }
}
