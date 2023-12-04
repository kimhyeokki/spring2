package com.jjang051.board.exception;

import com.jjang051.board.code.ErrorCode;
import com.jjang051.board.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

//중간에 낚아채서 작동한다.
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    //여기에 에러 다모아서 쓴다.

    @ExceptionHandler(BoardException.class)
    @ResponseBody
    public ErrorDto runtimeHandler(BoardException e){
         ErrorDto response = ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .message(e.getDetailMessage())
                .build();
        return response;
    }
    @ExceptionHandler(MemberException.class)
    @ResponseBody
    public ErrorDto bad(MemberException e){
        ErrorDto response = ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .message(e.getMessage())
                .build();
        return response;
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ErrorDto memberRuntimeHandler(SQLException e){
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.DUPLICATE_MEMBER)
                .message("알수 없는 에러로 회원가입이 되지 않습니다.")
                .build();
        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorDto anonymous(RuntimeException e){
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .message(ErrorCode.INVALID_REQUEST.getMessage())
                .build();
        return response;
    }
}
