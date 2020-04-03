package com.ying.background.controller;

import com.ying.background.dao.model.ReaderCard;
import com.ying.background.model.BookInfo;
import com.ying.background.model.Lend;
import com.ying.background.services.book.IBookService;
import com.ying.background.services.lend.ILendService;
import com.ying.background.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LendController extends BaseController {

    @Autowired
    private IBookService bookServiceImpl;

    @Autowired
    private ILendService lendServiceImpl;

    @RequestMapping("/lendbook.html")
    public ModelAndView bookLend(){
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        BookInfo book = bookServiceImpl.getBookDetail(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_lend");
        modelAndView.addObject("book",book);
        return modelAndView;
    }

    @RequestMapping("/lendbookdo.html")
    public String bookLendDo(RedirectAttributes redirectAttributes, int readerId){
        long bookId = Integer.parseInt(request.getParameter("id"));
        Lend lend = new Lend();
        lend.setBookId(bookId);
        lend.setReaderId(readerId);
        lend.setCreateId(getCid());
        boolean lendsucc = lendServiceImpl.bookLend(lend);
        if (lendsucc){
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
            return "redirect:/allbooks.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "图书借阅失败！");
            return "redirect:/allbooks.html";
        }


    }

    @RequestMapping("/returnbook.html")
    public String bookReturn(Integer flag, RedirectAttributes redirectAttributes){
        if(flag == null){
            flag = 0;
        }
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Lend lend = new Lend();
        lend.setBookId(bookId);
        lend.setModifyId(getCid());
        boolean retSucc = lendServiceImpl.returnBook(lend);
        if(flag == 0){
            if (retSucc){
                redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
                return "redirect:/allbooks.html";
            }
            else {
                redirectAttributes.addFlashAttribute("error", "图书归还失败！");
                return "redirect:/allbooks.html";
            }
        }else{
            if (retSucc){
                redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
                return "redirect:/lendlist.html";
            }
            else {
                redirectAttributes.addFlashAttribute("error", "图书归还失败！");
                return "redirect:/lendlist.html";
            }
        }

    }

    @RequestMapping("/lendlist.html")
    public ModelAndView lendList(String keyword, Integer deadline){
        if(deadline == null){
            deadline = 0;
        }
        int totalCount = lendServiceImpl.getQueryLendListCount(keyword, deadline);
        ModelAndView modelAndView=new ModelAndView("admin_lend_list");
        if(totalCount > 0){
            modelAndView.addObject("list",
                    lendServiceImpl.queryLendList(keyword, deadline, Constants.DEFAULT_INDEX, Constants.DEFAULT_SIZE));
        }
        modelAndView.addObject("totalCount", totalCount);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("deadline", deadline==0?null:deadline);
        return modelAndView;
    }

    @RequestMapping("/lendlist_do.html")
    public ModelAndView lendListDo(String keyword, Integer deadline, int curPage, int pageSize){
        if(deadline == null){
            deadline = 0;
        }
        int totalCount = lendServiceImpl.getQueryLendListCount(keyword, deadline);
        ModelAndView modelAndView=new ModelAndView("lend_list");
        if(totalCount > 0){
            if(pageSize <= 0){
                pageSize = 9;
            }
            if(curPage >= 1){
                curPage = curPage*pageSize - pageSize;
            }
            modelAndView.addObject("list",
                    lendServiceImpl.queryLendList(keyword, deadline, curPage, pageSize));
        }
        modelAndView.addObject("totalCount", totalCount);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("deadline", deadline==0?null:deadline);
        return modelAndView;
    }

    @RequestMapping("/mylend.html")
    public ModelAndView myLend(){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView=new ModelAndView("reader_lend_list");
        modelAndView.addObject("list",lendServiceImpl.myLend(readerCard.getReaderId(), Constants.DEFAULT_INDEX, Constants.DEFAULT_SIZE));
        return modelAndView;
    }




}
