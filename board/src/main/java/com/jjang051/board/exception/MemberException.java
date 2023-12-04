package com.jjang051.board.exception;

import com.jjang051.board.code.ErrorCode;
import lombok.Getter;

import lombok.Setter;

@Getter
public class MemberException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
    public MemberException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public MemberException(ErrorCode errorCode, String message){
        super(errorCode.getMessage());
        this.errorCode =errorCode;
        this.message = message;
    }
}
