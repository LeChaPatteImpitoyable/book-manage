package com.ying.background.services.customer;

public interface ITokenService {

	String renewLoginTokenAndTime(String mobile, String ip, String password, String uid);

	boolean verifyToken(int customerId, String token, String ip, String uid, int logo);

	int getCid(String token);
}
