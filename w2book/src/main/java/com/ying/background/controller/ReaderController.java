package com.ying.background.controller;

import com.ying.background.model.ReaderCard;
import com.ying.background.model.ReaderInfo;
import com.ying.background.services.reader.IReaderService;
import com.ying.background.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReaderController extends BaseController {

    @Autowired
    private IReaderService readerServiceImpl;

    @RequestMapping("allreaders.html")
    public ModelAndView allReaders(String keyword){
        int totalCount = readerServiceImpl.getQueryReaderInfosCount(keyword);
        ModelAndView modelAndView=new ModelAndView("admin_readers");
        if(totalCount > 0){
            modelAndView.addObject("readers",readerServiceImpl.queryReaderInfos(keyword, Constants.DEFAULT_INDEX, Constants.DEFAULT_SIZE));
        }
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject(Constants.TOTALCOUNT, totalCount);
        return modelAndView;
    }

    @RequestMapping("allreaders_do.html")
    public ModelAndView allReadersDo(String keyword, int curPage, int pageSize){
        int totalCount = readerServiceImpl.getQueryReaderInfosCount(keyword);
        ModelAndView modelAndView=new ModelAndView("admin_reader_list");
        if(totalCount > 0){
            if(pageSize <= 0){
                pageSize = 9;
            }
            if(curPage >= 1){
                curPage = curPage*pageSize - pageSize;
            }
            modelAndView.addObject("readers",readerServiceImpl.queryReaderInfos(keyword, curPage, pageSize));
        }
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject(Constants.TOTALCOUNT, totalCount);
        return modelAndView;
    }

    @RequestMapping("reader_delete.html")
    public String readerDelete( RedirectAttributes redirectAttributes){
        int readerId= Integer.parseInt(request.getParameter("readerId"));
        boolean success =readerServiceImpl.delReaderInfo(readerId, getCid());

        if(success){
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
            return "redirect:/allreaders.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
            return "redirect:/allreaders.html";
        }

    }
    @RequestMapping("/reader_info.html")
    public ModelAndView toReaderInfo() {
        ReaderCard readerCard =(ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = readerServiceImpl.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info");
        modelAndView.addObject("readerinfo",readerInfo);
        return modelAndView;
    }
    @RequestMapping(value = "/reader_info_do")
    @ResponseBody
    public Boolean getReaderInfoDo() {
        int readerId = Integer.parseInt(request.getParameter("readerId"));
        ReaderInfo readerInfo = readerServiceImpl.getReaderInfo(readerId);
        if(readerInfo == null || readerInfo.getReaderId() == 0){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/lenovo_reader.html")
    public ModelAndView getLenovoReader(String keyword) throws Exception {
        List<ReaderInfo> readers = readerServiceImpl.queryReaderInfos(keyword, Constants.DEFAULT_INDEX, Constants.NUMBER.FIVE);
        ModelAndView modelAndView=new ModelAndView("lenovo_reader");
        modelAndView.addObject("readers",readers);
        return modelAndView;
    }

    @RequestMapping("reader_edit.html")
    public ModelAndView readerInfoEdit(){
        int readerId = Integer.parseInt(request.getParameter("readerId"));
        ReaderInfo readerInfo = readerServiceImpl.getReaderInfo(readerId);
        ModelAndView modelAndView=new ModelAndView("admin_reader_edit");
        modelAndView.addObject("readerInfo",readerInfo);
        return modelAndView;
    }

    @RequestMapping("reader_edit_do.html")
    public String readerInfoEditDo( String name, String sex, String birth, String address, String telcode, RedirectAttributes redirectAttributes){
        int readerId = Integer.parseInt(request.getParameter("id"));
        ReaderCard readerCard = readerServiceImpl.getReaderCard(readerId);
        String oldName = readerCard.getName();
        int cid = getCid();
        if(!oldName.equals(name)){
            ReaderCard readerCard1 = new ReaderCard();
            readerCard1.setReaderId(readerCard.getReaderId());
            readerCard1.setName(name);
            readerCard1.setModifyId(cid);
            boolean succo = readerServiceImpl.editReaderCard(readerCard1);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }
            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(address);
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerId);
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(sex);
            readerInfo.setModifyId(cid);
            boolean succ = readerServiceImpl.editReaderInfo(readerInfo);
            if(succo&&succ){
                redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
                return "redirect:/allreaders.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
                return "redirect:/allreaders.html";
            }
        }
        else {
            System.out.println("部分修改");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }
            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(address);
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerId);
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(sex);
            readerInfo.setModifyId(cid);
            boolean succ = readerServiceImpl.editReaderInfo(readerInfo);
            if(succ){
                redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
                return "redirect:/allreaders.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
                return "redirect:/allreaders.html";
            }
        }

    }

    @RequestMapping("reader_add.html")
    public ModelAndView readerInfoAdd(){
        ModelAndView modelAndView=new ModelAndView("admin_reader_add");
        return modelAndView;

    }
    //用户功能--进入修改密码页面
    @RequestMapping("reader_repasswd.html")
    public ModelAndView readerRePasswd(){
        ModelAndView modelAndView=new ModelAndView("reader_repasswd");
        return modelAndView;
    }
    //用户功能--修改密码执行
    @RequestMapping("reader_repasswd_do.html")
    public String readerRePasswdDo( String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        int readerId=readerCard.getReaderId();
        String passwd=readerCard.getPasswd();
        int cid = getCid();
        if (newPasswd.equals(reNewPasswd)){
            if(passwd.equals(oldPasswd)){
                ReaderCard readerCard1 = new ReaderCard();
                readerCard1.setReaderId(readerCard.getReaderId());
                readerCard1.setPasswd(newPasswd);
                readerCard1.setModifyId(cid);
                boolean succ = readerServiceImpl.editReaderCard(readerCard1);
                if (succ){
                    ReaderCard readerCardNew = readerServiceImpl.getReaderCard(readerId);
                    request.getSession().setAttribute("readercard", readerCardNew);
                    redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                    return "redirect:/reader_repasswd.html";
                }else {
                    redirectAttributes.addFlashAttribute("succ", "密码修改失败！");
                    return "redirect:/reader_repasswd.html";
                }

            }else {
                redirectAttributes.addFlashAttribute("error", "修改失败,原密码错误");
                return "redirect:/reader_repasswd.html";
            }
        }else {
            redirectAttributes.addFlashAttribute("error", "修改失败,两次输入的新密码不相同");
            return "redirect:/reader_repasswd.html";
        }

    }
    //管理员功能--读者信息添加
    @RequestMapping("reader_add_do.html")
    public String readerInfoAddDo(String name,String sex,String birth,String address,String telcode,int readerId,RedirectAttributes redirectAttributes){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date nbirth=new Date();
        try{
            Date date=sdf.parse(birth);
            nbirth=date;
        }catch (ParseException e){
            e.printStackTrace();
        }
        int cid = getCid();
        ReaderInfo readerInfo = new ReaderInfo();
        readerInfo.setAddress(address);
        readerInfo.setBirth(nbirth);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setTelcode(telcode);
        readerInfo.setSex(sex);
        readerInfo.setCreateId(cid);
        boolean succ = readerServiceImpl.addReaderInfo(readerInfo);
        if(!succ){
            redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
            return "redirect:/allreaders.html";
        }
        ReaderCard readerCard = new ReaderCard();
        readerCard.setReaderId(readerId);
        readerCard.setName(name);
        readerCard.setCreateId(cid);
        boolean succc = readerServiceImpl.addReaderCard(readerCard);
        if (succc){
            redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
            return "redirect:/allreaders.html";
        }else {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
            return "redirect:/allreaders.html";
        }
    }
//读者功能--读者信息修改
    @RequestMapping("reader_info_edit.html")
    public ModelAndView readerInfoEditReader(){
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = readerServiceImpl.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info_edit");
        modelAndView.addObject("readerinfo",readerInfo);
        return modelAndView;

    }
    @RequestMapping("reader_edit_do_r.html")
    public String readerInfoEditDoReader( String name, String sex, String birth, String address, String telcode, RedirectAttributes redirectAttributes){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        int cid = getCid();
        if (!readerCard.getName().equals(name)){
            ReaderCard readerCard1 = new ReaderCard();
            readerCard1.setReaderId(readerCard.getReaderId());
            readerCard1.setName(name);
            readerCard1.setModifyId(cid);
            boolean succo = readerServiceImpl.editReaderCard(readerCard1);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }

            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(address);
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerCard.getReaderId());
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(sex);
            readerInfo.setModifyId(cid);
            boolean succ = readerServiceImpl.editReaderInfo(readerInfo);
            if(succ&&succo){
//                ReaderCard readerCardNew = loginService.findReaderCardByUserId(readerCard.getReaderId());
//                request.getSession().setAttribute("readercard", readerCardNew);
                redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
                return "redirect:/reader_info.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "信息修改失败！");
                return "redirect:/reader_info.html";
            }



        }else {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }

            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(address);
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerCard.getReaderId());
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(sex);
            readerInfo.setModifyId(cid);
            boolean succ = readerServiceImpl.editReaderInfo(readerInfo);
            if(succ){
                ReaderCard readerCardNew = readerServiceImpl.getReaderCard(readerCard.getReaderId());
                request.getSession().setAttribute("readercard", readerCardNew);
                redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
                return "redirect:/reader_info.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "信息修改失败！");
                return "redirect:/reader_info.html";
            }
        }
    }
}
