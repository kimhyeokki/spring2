package com.jjang051.board.dto;

import com.jjang051.board.code.ErrorCode;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {

    private ErrorCode errorCode;
    private String message;
}
