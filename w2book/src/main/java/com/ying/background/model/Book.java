package com.ying.background.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yingsy on 2018/5/20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = -3938920872988850129L;
    private Integer id;
    private String bookNo;
    private String bookName;
    private BigDecimal price;
    private Integer status;
    private String createTime;
    private Integer createId;
    private String modifyTime;
    private Integer modifyId;
    private Integer isDeleted;

}
