package com.ying.background.mapper;

import com.ying.background.model.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yingsy on 2018/5/20.
 */
@Component
@Mapper
public interface BookMapper {

    @Insert("insert into book(book_no,book_name,price,create_time,create_id,modify_time) " +
            "values(#{bookNo},#{bookName},#{price},datetime(),0,datetime())" )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertBook(Book book);

    @Select("select * from book where is_deleted=0")
    @Results(value = {@Result(property = "bookNo", column = "book_no"),@Result(property = "bookName", column = "book_name"),
            @Result(property = "createTime", column = "create_time"),@Result(property = "createId", column = "create_id"),
            @Result(property = "modifyTime", column = "modify_time"),@Result(property = "modifyId", column = "modify_id")})
    List<Book> selectAllBooks();
}
