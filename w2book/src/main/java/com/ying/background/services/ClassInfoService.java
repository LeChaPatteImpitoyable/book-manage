package com.ying.background.services;

import com.ying.background.dao.ClassDao;
import com.ying.background.dao.model.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yingsy on 2018/5/28.
 */
@Service
public class ClassInfoService {

    @Autowired
    private ClassDao classDao;

    public List<ClassInfo> getAllClassInfo(){
        return classDao.queryAllClassInfo();
    }

    public ClassInfo getClassInfo(int bookId){
        return classDao.getBook(bookId);
    }
}
