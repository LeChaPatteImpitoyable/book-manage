package com.ying.background.controller;

import com.ying.background.model.BookInfo;
import com.ying.background.services.ClassInfoService;
import com.ying.background.services.book.IBookService;
import com.ying.background.utils.Constants;
import com.ying.background.vo.BookAddCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class BookController extends BaseController {
    @Autowired
    private IBookService bookServiceImpl;

    @Autowired
    private ClassInfoService classInfoService;

    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(String keyword){
        int count = bookServiceImpl.getQueryBookCount(keyword);
        ModelAndView modelAndView = null;
        if (count > 0){
            List<BookInfo> books = bookServiceImpl.queryBooks(keyword, Constants.DEFAULT_INDEX, Constants.DEFAULT_SIZE);
            modelAndView = new ModelAndView(Constants.JSP_VIEW.ADMIN_BOOKS);
            modelAndView.addObject(Constants.BOOKS,books);
        }else{
            modelAndView = new ModelAndView(Constants.JSP_VIEW.ADMIN_BOOKS, Constants.ERROR,"没有匹配的图书");
        }
        modelAndView.addObject("keyword",keyword);
        modelAndView.addObject(Constants.TOTALCOUNT,count);
        return modelAndView;
    }
    @RequestMapping("/reader_querybook.html")
    public ModelAndView readerQueryBook(){
       return new ModelAndView(Constants.JSP_VIEW.READER_BOOK_QUERY);

    }
    @RequestMapping("/reader_querybook_do.html")
    public String readerQueryBookDo(String keyword, RedirectAttributes redirectAttributes){
        int count = bookServiceImpl.getQueryBookCount(keyword);
        if (count > 0){
            List<BookInfo> books = bookServiceImpl.queryBooks(keyword, Constants.DEFAULT_INDEX, Constants.DEFAULT_SIZE);
            redirectAttributes.addFlashAttribute(Constants.BOOKS, books);
            return Constants.JSP_VIEW.REDIRECT + Constants.JSP_VIEW.READER_QUERYBOOK;
        }
        else{
            redirectAttributes.addFlashAttribute(Constants.ERROR, "没有匹配的图书！");
            return Constants.JSP_VIEW.REDIRECT + Constants.JSP_VIEW.READER_QUERYBOOK;
        }

    }

    @RequestMapping("/allbooks.html")
    public ModelAndView allBook(){
        ModelAndView modelAndView = new ModelAndView(Constants.JSP_VIEW.ADMIN_BOOKS);
        int totalCount = bookServiceImpl.getQueryBookCount(null);
        if(totalCount > 0){
            modelAndView.addObject(Constants.BOOKS,bookServiceImpl.queryBooks(null, Constants.DEFAULT_INDEX, Constants.DEFAULT_SIZE));
        }
        modelAndView.addObject(Constants.TOTALCOUNT, totalCount);
        return modelAndView;
    }

    @RequestMapping("/allbooks_do.html")
    public ModelAndView allBookDo(String keyword, int curPage, int pageSize){
        ModelAndView modelAndView=new ModelAndView(Constants.JSP_VIEW.ADMIN_BOOK_LIST);
        int totalCount = bookServiceImpl.getQueryBookCount(keyword);
        if(totalCount > 0){
            if(pageSize <= 0){
                pageSize = 9;
            }
            if(curPage >= 1){
               curPage = curPage*pageSize - pageSize;
            }
            modelAndView.addObject(Constants.BOOKS,bookServiceImpl.queryBooks(keyword, curPage, pageSize));
        }
        modelAndView.addObject("keyword",keyword);
        modelAndView.addObject(Constants.TOTALCOUNT, totalCount);
        return modelAndView;
    }

    @RequestMapping("/deletebook.html")
    public String deleteBook(RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt(request.getParameter(Constants.BOOKID));
        boolean res = bookServiceImpl.delBook(bookId);

        if (res){
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
            return Constants.JSP_VIEW.REDIRECT+ Constants.JSP_VIEW.ALLBOOKS;
        }else {
            redirectAttributes.addFlashAttribute(Constants.ERROR, "图书删除失败！");
            return Constants.JSP_VIEW.REDIRECT+ Constants.JSP_VIEW.ALLBOOKS;
        }
    }

    @RequestMapping("/book_add.html")
    public ModelAndView addBook(){
        ModelAndView modelAndView = new ModelAndView(Constants.JSP_VIEW.ADMIN_BOOK_ADD);
        modelAndView.addObject("classInfos", classInfoService.getAllClassInfo());
        return modelAndView;
    }

    @RequestMapping("/book_add_do.html")
    public String addBookDo(BookAddCommand bookAddCommand, RedirectAttributes redirectAttributes){
        BookInfo book=new BookInfo();
        book.setPrice(bookAddCommand.getPrice());
        book.setState(bookAddCommand.getState());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setPressmark(bookAddCommand.getPressmark());
        book.setLanguage(bookAddCommand.getLanguage());


        boolean succ = bookServiceImpl.addBook(book);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
            return Constants.JSP_VIEW.REDIRECT+ Constants.JSP_VIEW.ALLBOOKS;
        }
        else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
            return Constants.JSP_VIEW.REDIRECT+ Constants.JSP_VIEW.ALLBOOKS;
        }
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(){
        long bookId=Integer.parseInt(request.getParameter(Constants.BOOKID));
        BookInfo book= bookServiceImpl.getBookDetail(bookId);
        ModelAndView modelAndView=new ModelAndView(Constants.JSP_VIEW.ADMIN_BOOK_EDIT);
        modelAndView.addObject(Constants.DETAIL,book);
        modelAndView.addObject("classInfos", classInfoService.getAllClassInfo());
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(BookAddCommand bookAddCommand, RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt( request.getParameter("id"));
        BookInfo book=new BookInfo();
        book.setBookId(bookId);
        book.setPrice(bookAddCommand.getPrice());
        book.setState(bookAddCommand.getState());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setPressmark(bookAddCommand.getPressmark());
        book.setLanguage(bookAddCommand.getLanguage());
        boolean succ = bookServiceImpl.editBook(book);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
            return Constants.JSP_VIEW.REDIRECT+ Constants.JSP_VIEW.ALLBOOKS;
        }
        else {
            redirectAttributes.addFlashAttribute(Constants.ERROR, "图书修改失败！");
            return Constants.JSP_VIEW.REDIRECT+ Constants.JSP_VIEW.ALLBOOKS;
        }
    }


    @RequestMapping("/bookdetail.html")
    public ModelAndView bookDetail(){
        long bookId=Integer.parseInt(request.getParameter(Constants.BOOKID));
        BookInfo book = bookServiceImpl.getBookDetail(bookId);
        ModelAndView modelAndView=new ModelAndView(Constants.JSP_VIEW.ADMIN_BOOK_DETAIL);
        modelAndView.addObject(Constants.DETAIL,book);
        return modelAndView;
    }



    @RequestMapping("/readerbookdetail.html")
    public ModelAndView readerBookDetail(){
        long bookId=Integer.parseInt(request.getParameter(Constants.BOOKID));
        BookInfo book = bookServiceImpl.getBookDetail(bookId);
        ModelAndView modelAndView=new ModelAndView(Constants.JSP_VIEW.READER_BOOK_DETAIL);
        modelAndView.addObject(Constants.DETAIL,book);
        return modelAndView;
    }



}
