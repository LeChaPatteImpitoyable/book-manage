package com.ying.background.utils;

/**
 * 标题: personal-tax
 * 描述:
 * 版权: 税友软件集团股份有限公司
 * 作者: caizh
 * 时间: 2018/1/19 10:09
 */
public final class RespGenerator {
    private RespGenerator() {
    }

    /**
     * @return
     */
    public static <T> ApiResponse<T> successful() {
        return new ApiResponse<T>().setCode(RespCode.SUCCESS.getCode());
    }

    /**
     * @param data
     * @return
     */
    public static <T> ApiResponse<T> successful(T data) {
        return new ApiResponse<T>().setCode(RespCode.SUCCESS.getCode()).setData(data);
    }

    /**
     * @param code
     * @return
     */
    public static <T> ApiResponse<T> fail(String code) {
        return new ApiResponse<T>().setCode(code);
    }

    public static <T> ApiResponse<T> fail(RespCode respCode) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(respCode.getCode());
        apiResponse.setMessage(respCode.getMessage());
        return apiResponse;
    }

    /**
     * @param code
     * @param args
     * @return
     */
    public static <T> ApiResponse<T> fail(String code, String... args) {
        // TODO args国际化支持，待开发
        return new ApiResponse<T>().setCode(code);
    }
}
