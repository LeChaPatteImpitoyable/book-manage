package com.ying.background.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookInfo implements Serializable {
    private static final long serialVersionUID = -4641786416858180326L;
    private Long bookId;

    private String name;

    private String author;

    private String publish;

    private String isbn;

    private String language;

    private BigDecimal price;

    private Date pubdate;

    private String classId;

    private Integer pressmark;

    private Short state;

    private Date createTime;

    private Integer createId;

    private Date modifyTime;

    private Integer modifyId;

    private Integer isDeleted;

    private String introduction;
}
