package com.khk11.outstargram.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {

    @NotBlank
    @Size(min = 4, max=20)
    private String userId;

    @NotBlank
    @Size(min = 4, max=20)
    private String password;

    @Email(message = "이메일 형식이 맞질 않습니다.")
    private String email;

    private String name;

}
