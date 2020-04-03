package com.ying.background.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyCodeSession implements Serializable {
	private static final long serialVersionUID = -4133479356058432064L;
	private int id;
	private String token;
	private String ip;
	private String mobile;
	private String password;
	private String uid;
	private long time;

}
