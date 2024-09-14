package com.example.coindesksprigboot.response;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private ApiResponseEnum code;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(ApiResponseEnum code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(ApiResponseEnum code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiResponseEnum.SUCCESS, ApiResponseEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ApiResponse<T> error(ApiResponseEnum code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
