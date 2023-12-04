package com.khk11.isotope.service;

import com.khk11.isotope.dao.MemberJoinDao;
import com.khk11.isotope.dto.CustomUserDetails;
import com.khk11.isotope.dto.MemberJoin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    //여기서 로그인한 값을 낚아채서 확인한다.
    private final MemberJoinDao memberJoinDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인하면 여기로 들어옴==={}",username);
        MemberJoin loggedMember = memberJoinDao.loginMember(username);
        log.info("로그인하면 여기로 들어옴==={}",loggedMember);
        //로그인 정보들 가지고 온다.
        if(loggedMember!=null){
            // 아래의 인스턴스에 loggedMember 넣어준다.
           return new CustomUserDetails(loggedMember);
        }
        return new CustomUserDetails(loggedMember);
    }
}
