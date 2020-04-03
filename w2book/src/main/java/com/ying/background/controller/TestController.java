package com.ying.background.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingsy on 2018/5/27.
 */
@Controller
public class TestController {

    @RequestMapping("/lenovo_box_demo.html")
    public ModelAndView toLenovoBox(){
        return new ModelAndView("lenovo_box_demo");
    }

    @RequestMapping(value = "/dev_lns.html")
    public ModelAndView getDivLns(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        int readerId= Integer.parseInt(request.getParameter("readerId"));
//        ReaderInfo readerInfo=readerInfoService.getReaderInfo(readerId);
        List<String> unames = new ArrayList<String>();
        unames.add("55555");
        unames.add("53242");
        unames.add("53242");
        unames.add("53242");
        unames.add("53242");
        unames.add("53242");
        ModelAndView modelAndView=new ModelAndView("divLns");
        modelAndView.addObject("unames",unames);
        return modelAndView;
    }
}
