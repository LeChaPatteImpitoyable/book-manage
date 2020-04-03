package com.ying.background.mapper;

import com.ying.background.model.ReaderCard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ReaderCardMapper {

    int insert(ReaderCard record);

    ReaderCard selectByPrimaryKey(Integer readerId);

    int updateByPrimaryKeySelective(ReaderCard record);

}