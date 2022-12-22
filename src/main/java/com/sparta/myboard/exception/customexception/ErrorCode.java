package com.sparta.myboard.exception.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400 ERROR
    USER_NOT_FOUND(BAD_REQUEST.value(), "회원을 찾을 수 없습니다."),
    OVERLAP_LOGIN_ID(BAD_REQUEST.value(), "중복된 loginid 입니다."),
    OVERLAP_NICKNAME(BAD_REQUEST.value(), "중복된 nickname 입니다."),
    MISMATCH_PASSWORD(BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    INVALID_TOKEN(BAD_REQUEST.value(), "토큰이 유효하지 않습니다."),
    EXPIRED_TOKEN(BAD_REQUEST.value(), "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(BAD_REQUEST.value(), "지원되지 않는 토큰입니다."),
    NOT_ALLOW_UPDATE(BAD_REQUEST.value(), "작성자만 수정할 수 있습니다."),
    NOT_ALLOW_DELETE(BAD_REQUEST.value(), "작성자만 삭제할 수 있습니다."),
    NOT_ALLOW_WITHDRAWAL(BAD_REQUEST.value(), "회원탈퇴를 실패했습니다."),
    POST_NOT_FOUND(BAD_REQUEST.value(), "게시글을 찾을 수 없습니다"),
    COMMENT_NOT_FOUND(BAD_REQUEST.value(), "댓글을 찾을 수 없습니다");

    private final int statusCode;
    private final String errorMessage;

}
