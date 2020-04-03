package com.ying.background.utils;


/**
 * enum of xmen code 
 *
 */
public enum RespCode {

    SUCCESS("SUCCESS", "SUCCESS", "请求成功"),
    ACCOUNT_OR_PASSWOED_ERROR("ACCOUNT_OR_PASSWOED_ERROR", "User name or password error", "用户名或密码错误"),
    TOKEN_IS_NULL("TOKEN_IS_NULL", "Token is null", "未获取到token"),
    RESP_CODE_NULL("RESP_CODE_NULL","Resp code is null", "返回值为null"),
    ;//没有权限


    private RespCode(String code, String msg, String message){
    	this.code =code;
    	this.msg = msg;
    	this.message = message;
    }
    
    private RespCode(){}
    
    private String code;
    private String msg;
    private String message;
    
    public String getCode(){
        return this.code;
    }

	public String getMsg() {
		return msg;
	}

    public String getMessage() {
        return message;
    }
}
