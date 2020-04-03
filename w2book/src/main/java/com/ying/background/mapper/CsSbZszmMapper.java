package com.ying.background.mapper;

import com.ying.background.dao.model.CsSbZszm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CsSbZszmMapper {

    CsSbZszm selectByPrimaryKey(Long pzxh);

    List<CsSbZszm> selectAll();
}
