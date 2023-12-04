package com.jjang051.board.service;

import com.jjang051.board.code.ErrorCode;
import com.jjang051.board.dao.MemberDao;
import com.jjang051.board.dto.JoinDto;
import com.jjang051.board.dto.LoginDto;
import com.jjang051.board.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.SQLException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    public int insertMember(@ModelAttribute JoinDto joinDto){
        //이름에  비속어들어 있으면 BAD_NAME 에러코드 발생시키기("이름에 욕이 들어가면 안됩니다.")
        //MemberException
        if(joinDto.getName().contains("개새")){
            throw new MemberException(ErrorCode.BAD_NAME);
        }
        if(memberDao.duplicationEmail(joinDto.getEmail())>0){
            throw new MemberException(ErrorCode.DUPLICATE_EMAIL);
        }
       JoinDto insertJoinDto =joinDto.builder()
                .id(joinDto.getId())
                .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .build();
       //여기에 MemberException을 발생시켜 오류 처리 해보기
        // 오류 코드는 DUPLICATE_MEMBER로 해보기
       int result = memberDao.insertMember(insertJoinDto);
        log.info("result==={}",result);
        if(result<=0) {
            throw new MemberException(ErrorCode.DUPLICATE_MEMBER);
        }
        return result;
    }

    @Transactional
    public int deleteMember(@ModelAttribute LoginDto loginDto){
        int result=0;
        JoinDto dbLoginDto =memberDao.loginMember(loginDto.getId());
        // 위에 코드는 사용자의 정보를 가져온다.
        if(bCryptPasswordEncoder.matches(loginDto.getPassword(),dbLoginDto.getPassword())){
            /*String state = null;*/
            memberDao.insertDeleteMember(dbLoginDto);
            /*if(state==null){
            throw new RuntimeException("에러남");
            }*/
            result = memberDao.deleteMember(loginDto);
        }
        return result;
    }

    public int updateMember(@ModelAttribute JoinDto joinDto){
        int result=0;
        JoinDto dbLoginDto =memberDao.loginMember(joinDto.getId());
        // 위에 코드는 사용자의 정보를 가져온다.
        if(bCryptPasswordEncoder.matches(joinDto.getPassword(),dbLoginDto.getPassword())){
            result = memberDao.updateMember(joinDto);
        }
        return result;
    }
}
