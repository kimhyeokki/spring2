package com.khk11.isotope.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberJoin {
    @NotBlank
    @Size(min=3,max=20,message = "3~20글자입니다.")
    private String id;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$",message = "영문자 숫자 조합해서 8자 16자 이하로 입력해주세요")
    private String password;
    @NotBlank
    @Size(min=2,max=20,message = "2~20글자입니다.")
    private String name;
    @NotBlank
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
    private String userrole;
    private MultipartFile profile;
    private String profiles;
    private int age;
}
