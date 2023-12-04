package com.jjang051.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JoinDto {
    @NotBlank
    public String id;
    private String password;
    private  String name;
    private String email;
    private String userrole;
    private Integer status;
    private Date regdate;
}
