package com.ying.background.services.customer.impl;

import com.ying.background.mapper.VerifyCodeSessionMapper;
import com.ying.background.model.VerifyCodeSession;
import com.ying.background.services.customer.ITokenService;
import com.ying.background.utils.AES128;
import com.ying.background.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TokenService implements ITokenService {
	@Autowired
	private VerifyCodeSessionMapper verifyCodeSessionMapper;
	
	private String tokenAesKey="w2book";

	@Override
	public String renewLoginTokenAndTime ( String mobile, String ip, String password, String uid) {

		String token = generateLoginToken(mobile, password, ip, String.valueOf(System.currentTimeMillis()), uid, tokenAesKey);

		VerifyCodeSession verifyCodeSession = verifyCodeSessionMapper.getVerifyCodeSession(mobile, uid);
		if (verifyCodeSession == null ){
			verifyCodeSession = new VerifyCodeSession();
			verifyCodeSession.setIp(ip);
			verifyCodeSession.setMobile(mobile);
			verifyCodeSession.setPassword(password);
			verifyCodeSession.setTime(System.currentTimeMillis());
			verifyCodeSession.setToken(token);
			verifyCodeSession.setUid(uid);
			insertVerifyCodeSession(verifyCodeSession);
		}else{
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("token", token);
			paramMap.put("time", System.currentTimeMillis());
			paramMap.put("uid", uid);
			paramMap.put("mobile", mobile);
			verifyCodeSessionMapper.updateVerifyCodeSession(paramMap);
		}
		return token;
	}

	private void insertVerifyCodeSession(VerifyCodeSession verifyCodeSession){
		verifyCodeSessionMapper.insertVerifyCodeSession(verifyCodeSession);
	}

   private  String generateLoginToken(String mobile, String password, String ip, String lastLoginTime, String cid,String key) {
	        String original = "[mobile:{0}],[password:{1}],[IP:{2}],[time:{3}],[cid:{4}]";
	        String str = MessageFormat.format(original, mobile, password, ip, lastLoginTime, cid);
	        return AES128.encryptAES(str,key);
	}

	@Override
	public boolean verifyToken (int customerId, String token, String ip, String uid, int logo){
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(ip) ){

			return false;
		}
		long expireTime = 1 * Constants.DAY ;//有效期一天
		String oldToken = AES128.decrypt(token, tokenAesKey);
		if (StringUtils.isEmpty(oldToken) ){
			return false;
		}

		String oldMobile = obtain(oldToken, "mobile");
		if (StringUtils.isEmpty(oldMobile)){
			return false;
		}
		String oldTime = obtain(oldToken, "time");
		long oldLoginTime = Long.parseLong(oldTime);
		long now = System.currentTimeMillis();
		//token过期
		if((now - oldLoginTime)  <= expireTime - 1) {
			return true;
		}else {
			log.error("token过期");
			return false;
		}
	}

	@Override
	public int getCid(String token) {
		token = AES128.decrypt(token, tokenAesKey);
		String cid = obtain(token, "cid");
		if(StringUtils.isEmpty(cid)){
			return Constants.NUMBER.ZERO;
		}
		return Integer.valueOf(cid);
	}

	private String obtain ( String token, String mark ) {
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(mark) ) {
			return null;
		}
		String[] tokens = token.split(Constants.COMMA_SIGN);
		String str = "";
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].contains(mark) ) {
				str = tokens[i];
				break;
			}
		}
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		int fromId = str.indexOf(Constants.KEY_VALUE_SIGN);
		return str.substring(fromId + 1, str.length() - 1);
	}
}
