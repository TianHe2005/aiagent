package com.example.aiagent.dto;

import java.util.Map;
import lombok.Data;

@Data
public class ResponseResult<T> {
    private T data;
    private Meta meta;

    @Data
    public static class Meta {
        private Integer code;
        private String msg;

        public Meta(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public ResponseResult(T data, Integer code, String msg) {
        this.data = data;
        this.meta = new Meta(code, msg);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(data, 200, "操作成功");
    }

    public static <T> ResponseResult<T> success(T data, String msg) {
        return new ResponseResult<>(data, 200, msg);
    }

    public static <T> ResponseResult<T> fail(String msg) {
        return new ResponseResult<>(null, 400, msg);
    }

    public static <T> ResponseResult<T> fail(Integer code, String msg) {
        return new ResponseResult<>(null, code, msg);
    }

    public static <T> ResponseResult<T> fail(String msg, Map<String, String> data) {
        @SuppressWarnings("unchecked")
        ResponseResult<T> result = (ResponseResult<T>) new ResponseResult<>(data, 400, msg);
        return result;
    }
}