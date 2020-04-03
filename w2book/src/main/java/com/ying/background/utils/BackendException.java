package com.ying.background.utils;

import org.springframework.util.StringUtils;

public class BackendException extends RuntimeException{

    
    private static final long serialVersionUID = 1L;

    private RespCode respCode;
    
    public BackendException(){
        super();
    }
    
    
    public BackendException(String msg){
        super(msg);
    }
    
    public BackendException(String msg, Throwable e){
        super(msg, e);
    };
    
    
    public BackendException(Throwable e){
        super(e);
    }
    
    
    public BackendException(RespCode code){
        super("code: " + code.getCode() + ", msg: " + code.getMsg());
        this.respCode = code;
    }
    
    
    public BackendException(RespCode code, Throwable e){
        super("code: " + code.getCode() + ", msg: " + code.getMsg(), e);
        this.respCode = code;
    }
    

    public RespCode getRespCode(){
        if(StringUtils.isEmpty(this.respCode)){
            return RespCode.RESP_CODE_NULL;
        }
        return this.respCode;
    }
        
}
