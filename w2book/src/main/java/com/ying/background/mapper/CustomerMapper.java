package com.ying.background.mapper;

import com.ying.background.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created by yingsy on 2018/6/3.
 */
@Component
@Mapper
public interface CustomerMapper {

    @Select("select * from customer where LOGIN_ACCOUNT = #{account} and  PASSWORD = #{password}")
    Customer toLogin(@Param("account") String accont, @Param("password") String password);

    @Select("select * from customer where id = #{cid}")
    Customer selectCustomerById(int cid);

    @Update("update customer set PASSWORD = #{password},modify_time=now() where id = #{cid}")
    int updatePasswd(@Param("cid") int cid, @Param("password") String password);
}
