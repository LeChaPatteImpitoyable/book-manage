package com.ying.background.services;

import com.alibaba.fastjson.JSON;
import com.ying.background.dao.ReaderInfoDao;
import com.ying.background.dao.model.ReaderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderInfoService {
    Logger logger = LoggerFactory.getLogger(ReaderInfoService.class);
    private ReaderInfoDao readerInfoDao;
    @Autowired
    public void setReaderInfoDao(ReaderInfoDao readerInfoDao) {
        this.readerInfoDao = readerInfoDao;
    }

    public List<ReaderInfo> readerInfos(){
        return readerInfoDao.getAllReaderInfo();
    }

    public int getReaderInfosCount(){
        return readerInfoDao.queryAllBookCount();
    }

    public List<ReaderInfo> gePagetReaderInfos(int offset, int length){
        return readerInfoDao.getPageReaderInfo(offset, length);
    }

    public boolean deleteReaderInfo(int readerId){
        return readerInfoDao.deleteReaderInfo(readerId)>0;
    }

    public ReaderInfo getReaderInfo(int readerId){
        ReaderInfo readerInfo = readerInfoDao.findReaderInfoByReaderId(readerId);
        logger.info("----------readerInfo:"+ JSON.toJSONString(readerInfo));
        return readerInfo;
    }
    public boolean editReaderInfo(ReaderInfo readerInfo){
        return readerInfoDao.editReaderInfo(readerInfo)>0;
    }
    public boolean addReaderInfo(ReaderInfo readerInfo){
        return  readerInfoDao.addReaderInfo(readerInfo)>0;
    }
}
