package com.khk11.jap.dto;


import com.khk11.jap.entity.Member02;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDto {

    private String userId;
    private String nickName;
    private String gender;
    private String email;
    private Integer age;

    public static MemberDto fromEntity(Member02 member02){  //static을 사용해서 MemberDto.fromEntity() 로 바로 생성 가능
        return  MemberDto.builder()
                .userId(member02.getUserId())
                .nickName(member02.getNickName())
                .gender(member02.getGender())
                .email(member02.getEmail())
                .age(member02.getAge())
                .build();
    }
}
