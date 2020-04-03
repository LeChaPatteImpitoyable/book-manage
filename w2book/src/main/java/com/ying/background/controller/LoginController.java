package com.ying.background.controller;

import com.ying.background.model.Customer;
import com.ying.background.services.customer.ICustomerService;
import com.ying.background.utils.ApiResponse;
import com.ying.background.utils.HttpUtil;
import com.ying.background.utils.RespCode;
import com.ying.background.utils.RespGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

//标注为一个Spring mvc的Controller
@Controller
public class LoginController extends BaseController {

    @Autowired
    private ICustomerService customerService;

    //负责处理login.html请求
    @RequestMapping(value = {"/","/login.html"})
    public String toLogin(){
        request.getSession().invalidate();
        return "index";
    }
    @RequestMapping("/logout.html")
    public String logout(HttpServletResponse response) {
        request.getSession().invalidate();
        HttpUtil.removeCookie(response, HttpUtil.BOOK_TOKEN);
        return "redirect:/login.html";
    }


    //负责处理loginCheck.html请求
    //请求参数会根据参数名称默认契约自动绑定到相应方法的入参中
    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse loginCheck(HttpServletResponse response){
        String account = request.getParameter("account");
        String passwd = request.getParameter("passwd");
        Customer customer = customerService.login(account, passwd, HttpUtil.getClientIp(request), "0");
        if(customer == null){
            return RespGenerator.fail(RespCode.ACCOUNT_OR_PASSWOED_ERROR);
        }else{
            HttpUtil.addCookieValue(response, HttpUtil.BOOK_TOKEN, customer.getToken());
            return RespGenerator.successful();
        }
    };
    @RequestMapping("/admin_main.html")
    public ModelAndView toAdminMain() {

            return new ModelAndView("admin_main");
    }


    @RequestMapping("/reader_main.html")
    public ModelAndView toReaderMain() {

        return new ModelAndView("reader_main");
    }



    @RequestMapping("/admin_repasswd.html")
    public ModelAndView reAdminPasswd() {

        return new ModelAndView("admin_repasswd");
    }

    @RequestMapping("/admin_repasswd_do")
    public String reAdminPasswdDo(String oldPasswd, String newPasswd, RedirectAttributes redirectAttributes, HttpServletResponse response) {
        Customer customer = customerService.getCustomer(getCid());
        String passwd = customer.getPassword();
        if(passwd.equals(oldPasswd)){
            boolean succ = customerService.updatePassword(customer.getId(), newPasswd);
            if (succ){
                HttpUtil.removeCookie(response, HttpUtil.BOOK_TOKEN);
                return "redirect:/login.html";
            }
            else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/admin_repasswd.html";
            }
        }else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/admin_repasswd.html";
        }
    };

    //配置404页面
     @RequestMapping("*")
     public String notFind(){
     return "404";
       }


}
