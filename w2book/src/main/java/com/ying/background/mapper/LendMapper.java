package com.ying.background.mapper;

import com.ying.background.model.Lend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Mapper
public interface LendMapper {

    int insert(Lend record);

    int bookReturn(Lend record);

    BigDecimal queryLendListCount(@Param("keyword")String keyword, @Param("deadline") int deadline);

    List<Lend> queryLendList(@Param("keyword") String keyword, @Param("deadline") int deadline, @Param("offset")int offset, @Param("length")int length);

    List<Lend> selectLendByReadeId(@Param("readerId") int readerId, @Param("offset")int offset, @Param("length")int length);
}