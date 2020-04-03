package com.ying.background.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReaderInfo implements Serializable {
    private static final long serialVersionUID = -8027170944270694492L;
    private Integer readerId;

    private String name;

    private String sex;

    private Date birth;

    private String address;

    private String telcode;

    private Date createTime;

    private Integer createId;

    private Date modifyTime;

    private Integer modifyId;

    private Integer isDeleted;


}
