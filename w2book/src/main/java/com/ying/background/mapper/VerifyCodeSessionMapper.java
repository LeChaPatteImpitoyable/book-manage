package com.ying.background.mapper;

import com.ying.background.model.VerifyCodeSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Mapper
public interface VerifyCodeSessionMapper
{
   /**
    * 
    * @param mobile
    * @return
    */
//   @Select("select * from verifycodesession where mobile=#{mobile} and uid=#{uid}")
	 VerifyCodeSession getVerifyCodeSession(@Param("mobile") String mobile, @Param("uid") String uid);
   
	/**
	 *
	 * @param verifyCodeSession
	 * @return
	 */
	 int insertVerifyCodeSession(VerifyCodeSession verifyCodeSession);

	/**
	 *
	 * @param param
	 * @return
	 */
    int updateVerifyCodeSession(Map<String, Object> param);

   /**
    * 退出登录
    * @param token
    * @return
    */
    int deleteVerifyCodeSession(String token);
}
