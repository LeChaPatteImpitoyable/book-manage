package com.ying.background.services.lend;

import com.ying.background.model.Lend;

import java.util.List;

/**
 * Created by yingsy on 2018/6/7.
 */
public interface ILendService {

    int getQueryLendListCount(String keyword, int deadline);

    List<Lend> queryLendList(String keyword, int deadline, int index, int size);

    boolean returnBook(Lend lend);

    boolean bookLend(Lend lend);

    List<Lend> myLend(int readerId, int index, int size);

}
