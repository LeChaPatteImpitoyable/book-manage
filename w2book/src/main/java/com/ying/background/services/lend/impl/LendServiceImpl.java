package com.ying.background.services.lend.impl;

import com.ying.background.constant.EhCacheValueConstants;
import com.ying.background.mapper.LendMapper;
import com.ying.background.model.BookInfo;
import com.ying.background.model.Lend;
import com.ying.background.services.book.IBookService;
import com.ying.background.services.lend.ILendService;
import com.ying.background.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by yingsy on 2018/6/7.
 */
@Service
public class LendServiceImpl implements ILendService {

    @Autowired
    private LendMapper lendMapper;

    @Autowired
    private IBookService bookServiceImpl;

    @Override
    public int getQueryLendListCount(String keyword, int deadline) {
        BigDecimal bigDecimal = lendMapper.queryLendListCount(keyword, deadline);
        return bigDecimal == null ? Constants.NUMBER.ZERO : bigDecimal.intValue();
    }

    @Override
    @Cacheable(value = EhCacheValueConstants.LEND, key = "'queryLendList-['+#deadline+']['+#index+']['+#size+']'")
    public List<Lend> queryLendList(String keyword, int deadline, int index, int size) {
        return lendMapper.queryLendList(keyword, deadline, index, size);
    }

    @Transactional
    public boolean addLend(Lend lend) {
        return lendMapper.insert(lend) > 0;
    }

    @Transactional
    @Override
    public boolean returnBook(Lend lend) {
        boolean isOk = updateBookState(lend, Constants.BOOK_STATE.NOT_LEND);
        if(!isOk){
            return isOk;
        }
        lend.setBackDate(new Date());
        return lendMapper.bookReturn(lend) > 0;
    }

    @Transactional
    @Override
    public boolean bookLend(Lend lend) {
        boolean isOk = updateBookState(lend, Constants.BOOK_STATE.HAS_BORROWED);
        if(!isOk){
            return isOk;
        }
        return addLend(lend);
    }

    @Override
    public List<Lend> myLend(int readerId, int index, int size) {
        return lendMapper.selectLendByReadeId(readerId, index, size);
    }

    private boolean updateBookState(Lend lend, short bookState){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId(lend.getBookId());
        bookInfo.setState(bookState);
        bookInfo.setModifyId(lend.getCreateId());
        return bookServiceImpl.editBook(bookInfo);
    }
}
