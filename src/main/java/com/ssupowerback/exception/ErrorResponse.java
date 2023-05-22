package com.ssupowerback.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    /**
     * HTTP ErrorCode & ErrorMessage를 customize하여 Client에게 전송할 수 있도록 함
     */
    private int statusCode;
    private String message;

    // 생성자, getter, setter 생략

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // ErrorResponse 객체를 생성하여 반환하는 유틸리티 메서드
    public static ErrorResponse of(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(status.value());
        errorResponse.setMessage(message);
        return errorResponse;
    }
}
