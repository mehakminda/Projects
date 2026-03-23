package com.learning.telusko.service;

import com.learning.telusko.dao.ICustomerDao;
import com.learning.telusko.model.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerDao customerDao;

    @Override
    public List<CustomerInfo> getCustomers() {
        return (List<CustomerInfo>) customerDao.findAll();
    }
}
