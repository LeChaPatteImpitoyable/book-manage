package com.ying.background.controller;

import com.ying.background.services.customer.ITokenService;
import com.ying.background.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yingsy on 2018/6/3.
 */
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected ITokenService tokenService;

    protected int getCid(){
        String token = HttpUtil.getCookieValue(request, HttpUtil.BOOK_TOKEN);
        return tokenService.getCid(token);
    }
}
