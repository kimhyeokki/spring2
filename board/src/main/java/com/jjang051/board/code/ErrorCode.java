package com.jjang051.board.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //내가 만든 오류들....
    INVALID_REQUEST("잘못된 요청입니다."),
    DUPLICATE_MEMBER("중복된 아이디입니다."),
    BAD_NAME("이름에 비속어가 들어가면 안됩니다."),
    DUPLICATE_EMAIL("중복된 이메일입니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생하였습니다.");

    private final String message;
}
