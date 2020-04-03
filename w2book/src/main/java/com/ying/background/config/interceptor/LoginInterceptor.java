package com.ying.background.config.interceptor;

import com.ying.background.services.customer.ITokenService;
import com.ying.background.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yingsy on 2018/5/30.
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private ITokenService tokenService;

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
        String uri = req.getRequestURI();
        String appType = req.getParameter("appType");
        int appTy = 0;
        if (!StringUtils.isEmpty(appType)) {
            appTy = Integer.valueOf(appType);
        } else {
            appType = "0";
        }
        log.info("-----------------------auto------------------------------");
        String token = HttpUtil.getCookieValue(req, HttpUtil.BOOK_TOKEN);
        log.info("token:{}",token);
        if(StringUtils.isEmpty(token)){
            returnToLoginHtml(resp, appTy);
            return false;
        }else{
           return tokenService.verifyToken(0, token, HttpUtil.getClientIp(req), null, 0);
        }
    }

    private void returnToLoginHtml(HttpServletResponse resp, int appTy) throws Exception{
        resp.sendRedirect("/login.html");
    }
}
