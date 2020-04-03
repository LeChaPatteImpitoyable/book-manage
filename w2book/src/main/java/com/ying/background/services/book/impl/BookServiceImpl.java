package com.ying.background.services.book.impl;

import com.ying.background.aspect.MyCacheable;
import com.ying.background.constant.EhCacheValueConstants;
import com.ying.background.mapper.BookInfoMapper;
import com.ying.background.model.BookInfo;
import com.ying.background.services.book.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yingsy on 2018/5/20.
 */
@Service
@Slf4j
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public int getQueryBookCount(String searchWord) {
        BigDecimal bigDecimal = bookInfoMapper.queryBooksCount(searchWord);
        return bigDecimal == null ? 0 : bigDecimal.intValue();
    }

    @Override
//    @Cacheable(value = EhCacheValueConstants.BOOK, key = "'queryBooks-['+#searchWord+']['+#offset+']['+#length+']'")
//    @MyCacheable(value = EhCacheValueConstants.BOOK, key = "'queryBooks-['+#searchWord+']['+#offset+']['+#length+']'", expireTime = 30 * 60 * 1000)
    public List<BookInfo> queryBooks(String searchWord, int offset, int length) {
        return bookInfoMapper.queryBooks(searchWord, offset, length);
    }

    @CacheEvict(value = EhCacheValueConstants.BOOK, allEntries = true)
    @Transactional
    @Override
    public boolean addBook(BookInfo bookInfo) {
        return bookInfoMapper.insert(bookInfo) > 0;
    }

    @Transactional
    @Override
    public boolean editBook(BookInfo bookInfo) {
        return bookInfoMapper.updateByPrimaryKeySelective(bookInfo) > 0;
    }

    @Override
    public BookInfo getBookDetail(Long bookId) {
        return bookInfoMapper.selectByPrimaryKey(bookId);
    }

    @CacheEvict(value = EhCacheValueConstants.BOOK, allEntries = true)
    @Transactional
    @Override
    public boolean delBook(Long bookId) {
        return bookInfoMapper.deleteBookByBookId(bookId) > 0;
    }

}
