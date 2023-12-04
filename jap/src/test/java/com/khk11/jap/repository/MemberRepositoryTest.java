package com.khk11.jap.repository;

import com.khk11.jap.entity.Member02;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest   //테스트 동작가능
    @Transactional //테스트 후 롤백가능
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("1+2는 3이다")
    @Test
    public void test(){
        int a=1;
        int b=2;
        int sum=3;
        Assertions.assertEquals(sum,a+b);
        //Assertions.assertThat(a+b).isEqualTo(sum);
        //Assertions 두개 어노테이션이 있다. 둘다 알필요 있음
    }

    public void joinMember(){
        for(int i=0;i<10;i++){
            Member02 member = new Member02();
            member = Member02.builder()
                    .userId("khk1103"+i)
                    .email("kkk"+i+"@naver.com")
                    .nickName("길동"+i)
                    .age(10+i)
                    .gender("남"+i)
                    .build();
            memberRepository.save(member);
        }
    }

    @Test
    @DisplayName("닉네임으로 조회")
    public void findByNickNameTest(){
        joinMember();
        List<Member02> memberList = memberRepository.findByNickName("길동2");
        Assertions.assertEquals(1,memberList.size());
        /*  for (Member02 item:memberList){
            System.out.println(item.toString());
        }*/

    }

    @Test
    @DisplayName("닉네임과 아이디로 조회")
    public void findByNickNameOrUserIdTest(){
        joinMember();
        List<Member02> member02List = memberRepository.findByNickNameOrUserId("길동2", "khk11031");
        Assertions.assertEquals(2,member02List.size());
    }

    @Test
    @DisplayName("15살보다 많은 사람 찾기")
    public void findByAgeGreaterThanTest(){
        joinMember();
        List<Member02> member02List = memberRepository.findByAgeGreaterThan(15);
        Assertions.assertEquals(4,member02List.size());
        for (Member02 member : member02List){
            System.out.println(member.toString());
        }
    }

    @Test
    @DisplayName("15살보다 많은 사람 찾기")
    public void findByAgeGreaterThanOrderByAgerDescTest(){
        joinMember();
        List<Member02> member02List = memberRepository.findByAgeGreaterThanOrderByAgeDesc(15);
        Assertions.assertEquals(4,member02List.size());
        for (Member02 member : member02List){
            System.out.println(member.toString());
        }
    }

}