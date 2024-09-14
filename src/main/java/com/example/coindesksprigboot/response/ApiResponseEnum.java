package com.example.coindesksprigboot.response;

public enum ApiResponseEnum {

    SUCCESS(200, "成功"),
    NOT_FOUND(404, "未找到"),
    INTERNAL_SERVER_ERROR(500, "內部伺服器錯誤"),
    BAD_REQUEST(400, "錯誤的請求");

    private final Integer code;
    private final String msg;

    ApiResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
