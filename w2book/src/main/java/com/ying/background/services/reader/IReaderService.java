package com.ying.background.services.reader;

import com.ying.background.model.ReaderCard;
import com.ying.background.model.ReaderInfo;

import java.util.List;

/**
 * Created by yingsy on 2018/6/6.
 */
public interface IReaderService {

    int getQueryReaderInfosCount(String keyword);

    List<ReaderInfo> queryReaderInfos(String keyword, int index, int size);

    boolean delReaderInfo(int readerId, int cid);

    ReaderInfo getReaderInfo(int readerId);

    boolean editReaderInfo(ReaderInfo readerInfo);

    boolean addReaderInfo(ReaderInfo readerInfo);

    boolean addReaderCard(ReaderCard readerCard);

    boolean editReaderCard(ReaderCard readerCard);

    boolean delReaderCard(int readerId, int cid);

    ReaderCard getReaderCard(int readerId);
}
