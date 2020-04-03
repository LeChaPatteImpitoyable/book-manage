package com.ying.background.services.customer.impl;

import com.ying.background.mapper.CustomerMapper;
import com.ying.background.model.Customer;
import com.ying.background.services.customer.ICustomerService;
import com.ying.background.services.customer.ITokenService;
import com.ying.background.utils.BackendException;
import com.ying.background.utils.RespCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by yingsy on 2018/6/3.
 */
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ITokenService tokenService;

    @Override
    public Customer login(String account, String password, String clientIp, String appType) {
        Customer customer = customerMapper.toLogin(account, password);
        if(customer == null){
            return customer;
        }
        String token = tokenService.renewLoginTokenAndTime(account, clientIp, appType, String.valueOf(customer.getId()));
        if(StringUtils.isEmpty(token)){
            throw new BackendException(RespCode.TOKEN_IS_NULL);
        }
        customer.setToken(token);
        return customer;
    }

    @Override
    public Customer getCustomer(int cid) {
        return customerMapper.selectCustomerById(cid);
    }

    @Override
    public boolean updatePassword(int cid, String passwd) {
        return customerMapper.updatePasswd(cid, passwd) > 0;
    }
}
