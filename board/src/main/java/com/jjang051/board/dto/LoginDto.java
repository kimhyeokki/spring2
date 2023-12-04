package com.jjang051.board.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    private String id;
    private String password;

}
