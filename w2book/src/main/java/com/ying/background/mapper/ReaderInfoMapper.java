package com.ying.background.mapper;

import com.ying.background.model.ReaderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Mapper
public interface ReaderInfoMapper {

    int insert(ReaderInfo record);

    int insertSelective(ReaderInfo record);

    ReaderInfo selectByPrimaryKey(Integer readerId);

    int updateByPrimaryKeySelective(ReaderInfo record);

    BigDecimal queryReaderInfosCount(@Param("keyword") String keyword);

    List<ReaderInfo> queryReaderInfos(@Param("keyword") String keyword, @Param("offset") int offset, @Param("length") int length);
}