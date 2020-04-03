package com.ying.background.mapper;

import com.ying.background.model.BookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Mapper
public interface BookInfoMapper {
    int insert(BookInfo record);

    BookInfo selectByPrimaryKey(Long bookId);

    List<BookInfo> selectAllBooks();

    int updateByPrimaryKey(BookInfo record);

    int updateByPrimaryKeySelective(BookInfo record);

    int deleteBookByBookId(Long bookId);

    BigDecimal queryBooksCount(@Param("searchWord") String searchWord);

    List<BookInfo> queryBooks(@Param("searchWord") String searchWord,@Param("offset") int offset,@Param("length") int length);
}