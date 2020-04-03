package com.ying.background.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 标题: personal-tax
 * 描述:
 * 版权: 税友软件集团股份有限公司
 * 作者: caizh
 * 时间: 2018/1/19 10:04
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse <T extends Object>{
    @Getter
    private String code;
    @Getter
    private String message;
    @Getter
    private T data;

    public ApiResponse<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public ApiResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
