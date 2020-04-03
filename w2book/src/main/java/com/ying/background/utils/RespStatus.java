package com.ying.background.utils;

import java.io.Serializable;


public class RespStatus implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2236993919061762740L;
    
    private String code = RespCode.SUCCESS.getCode();
    private String msg = RespCode.SUCCESS.getMsg();
    private RespCode respCode = RespCode.SUCCESS;
    
    /**
     * constructor
     */
    public RespStatus(){
    }
    
    /**
     * constructor specified parameter
     * @param respCode
     */
    public RespStatus(RespCode respCode){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
        this.respCode = respCode;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public RespCode getRespCode() {
		return respCode;
	}

	public void setRespCode(RespCode respCode) {
		this.respCode = respCode;
	}
    
    
    
    
    
}
