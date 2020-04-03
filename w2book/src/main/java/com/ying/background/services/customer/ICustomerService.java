package com.ying.background.services.customer;

import com.ying.background.model.Customer;

public interface ICustomerService {

    Customer login(String telephone, String password, String clientIp, String appType);

    Customer getCustomer(int cid);

    boolean updatePassword(int cid, String passwd);
}
