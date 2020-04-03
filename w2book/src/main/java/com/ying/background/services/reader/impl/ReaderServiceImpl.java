package com.ying.background.services.reader.impl;

import com.ying.background.mapper.ReaderCardMapper;
import com.ying.background.mapper.ReaderInfoMapper;
import com.ying.background.model.ReaderCard;
import com.ying.background.model.ReaderInfo;
import com.ying.background.services.reader.IReaderService;
import com.ying.background.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yingsy on 2018/6/6.
 */
@Service
public class ReaderServiceImpl implements IReaderService {

    @Autowired
    private ReaderCardMapper readerCardMapper;

    @Autowired
    private ReaderInfoMapper readerInfoMapper;


    @Override
    public int getQueryReaderInfosCount(String keyword) {
        BigDecimal bigDecimal = readerInfoMapper.queryReaderInfosCount(keyword);
        return bigDecimal == null ? 0 : bigDecimal.intValue();
    }

    @Override
    public List<ReaderInfo> queryReaderInfos(String keyword, int index, int size) {
        return readerInfoMapper.queryReaderInfos(keyword, index, size);
    }

    @Transactional
    @Override
    public boolean delReaderInfo(int readerId, int cid) {
        ReaderInfo readerInfo = new ReaderInfo();
        readerInfo.setReaderId(readerId);
        readerInfo.setModifyId(cid);
        readerInfo.setIsDeleted(Constants.IS_DELETED.YES);
        return readerInfoMapper.updateByPrimaryKeySelective(readerInfo) > 0;
    }

    @Override
    public ReaderInfo getReaderInfo(int readerId) {
        return readerInfoMapper.selectByPrimaryKey(readerId);
    }

    @Transactional
    @Override
    public boolean editReaderInfo(ReaderInfo readerInfo) {
        return readerInfoMapper.updateByPrimaryKeySelective(readerInfo) > 0;
    }

    @Transactional
    @Override
    public boolean addReaderInfo(ReaderInfo readerInfo) {
        ReaderInfo readerInfo1 = readerInfoMapper.selectByPrimaryKey(readerInfo.getReaderId());
        if(null != readerInfo1){
            return false;
        }
        return readerInfoMapper.insert(readerInfo) > 0;
    }

    @Transactional
    @Override
    public boolean addReaderCard(ReaderCard readerCard) {
        return readerCardMapper.insert(readerCard) > 0;
    }

    @Transactional
    @Override
    public boolean editReaderCard(ReaderCard readerCard) {
        return readerCardMapper.updateByPrimaryKeySelective(readerCard) > 0;
    }

    @Transactional
    @Override
    public boolean delReaderCard(int readerId, int cid) {
        ReaderCard readerCard = new ReaderCard();
        readerCard.setReaderId(readerId);
        readerCard.setModifyId(cid);
        readerCard.setIsDeleted(Constants.IS_DELETED.YES);
        return readerCardMapper.updateByPrimaryKeySelective(readerCard) > 0;
    }

    @Override
    public ReaderCard getReaderCard(int readerId) {
        return readerCardMapper.selectByPrimaryKey(readerId);
    }
}
