package com.khk11.jap.service;

import com.khk11.jap.dto.MemberDto;
import com.khk11.jap.entity.Member02;
import com.khk11.jap.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberDto join(MemberDto memberDto){
        //MemberDto memberDto  Dto를 값들을 엔티티로 만들 수 잇다. 빌드를 통해
       // Member02 dbMember = Member02.builder(memberDto.getUserId()).build();
        Member02 dbJoinMember = Member02.builder()
                .userId(memberDto.getUserId())
                .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                .role("ROLE_USER")
                .email(memberDto.getEmail())
                .nickName(memberDto.getNickName())
                .gender(memberDto.getGender())
                .age(memberDto.getAge())
                .build();
        Member02 member = memberRepository.save(dbJoinMember);
       MemberDto responseMember =  MemberDto.fromEntity(member);
        //Dto 반환할 때 즉 엔티티를 Dto로 반환하려면 HOW?? 빌드를 통해 반환가능
       // MemberDto.builder().userId(member.getUserId()).build();
        //but 이걸 일일히 하는 것은 멍청한 짓 Dto에서 메서드를 만들어서 쓰자.

        return responseMember;

    }

    public List<MemberDto> getAllMember() {
        List<Member02> member02List = memberRepository.findAll();
        List<MemberDto> memberList = new ArrayList<>();
        for(int i=0;i<member02List.size();i++)
        memberList.add(MemberDto.fromEntity(member02List.get(i)));
        //반복문돌려서 Dto로 바꾸고 memberList에 담아야함.
        return memberList;
    }

    public MemberDto getMemberInfo(String id) {
        Optional<Member02> member = memberRepository.findById(id);
        if(member.isPresent()) {
            MemberDto memberInfo = MemberDto.fromEntity(member.get());
            log.info("memberInfo===={}",memberInfo.toString());
            return memberInfo;
        }
        return null;
        //throw new NotFoundMember("찾는 사람이 없습니다.");
    }
    public MemberDto modifyMember(MemberDto memberDto) {
        Optional<Member02> member = memberRepository.findById(memberDto.getUserId());
        // jpa 에 id로 잡힌 컬럼의 이름이 같으면 update를 한다. 아니면 insert
        if(member.isPresent()) {
            Member02 updateMember = Member02.builder()
                    .userId(member.get().getUserId())
                    .age(memberDto.getAge())
                    .email(memberDto.getEmail())
                    .nickName(memberDto.getNickName())
                    .build();
            memberRepository.save(updateMember);
        }
        return null;
    }
    public boolean deleteMember(String id){
        Optional<Member02> member = memberRepository.findById(id);
        if(member.isPresent()){
            memberRepository.delete(member.get());
            return true;
        }
        return false;
    }
}
