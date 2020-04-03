package com.ying.background.services.book;

import com.ying.background.model.BookInfo;

import java.util.List;

/**
 * Created by yingsy on 2018/5/20.
 */
public interface IBookService {

    int getQueryBookCount(String searchWord);

    List<BookInfo> queryBooks(String searchWord, int offset, int length);

    boolean addBook(BookInfo bookInfo);

    boolean editBook(BookInfo bookInfo);

    BookInfo getBookDetail(Long bookId);

    boolean delBook(Long bookId);
}
