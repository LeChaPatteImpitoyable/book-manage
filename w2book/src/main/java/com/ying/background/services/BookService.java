package com.ying.background.services;

import com.ying.background.dao.BookDao;
import com.ying.background.dao.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> queryBook(String searchWord){
        return  bookDao.queryBook(searchWord);
    }

    public List<Book> queryPageBook(String searchWord, int offset, int length){
        return  bookDao.queryPageBook(searchWord, offset, length);
    }

    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public int getAllBooksCount(){
        return bookDao.queryAllBookCount();
    }

    public List<Book> getPageBooks(int offset, int length){
        return bookDao.getPageBooks(offset, length);
    }

    public int deleteBook(long bookId){
        return bookDao.deleteBook(bookId);
    }

    public boolean matchBook(String searchWord){
        return bookDao.matchBook(searchWord)>0;
    }

    public int matchBookCount(String searchWord){
        return bookDao.matchBook(searchWord);
    }

    public boolean addBook(Book book){
        return bookDao.addBook(book)>0;
    }

    public Book getBook(Long bookId){
        Book book=bookDao.getBook(bookId);
        return book;
    }
    public boolean editBook(Book book){
        return bookDao.editBook(book)>0;
    }

}
