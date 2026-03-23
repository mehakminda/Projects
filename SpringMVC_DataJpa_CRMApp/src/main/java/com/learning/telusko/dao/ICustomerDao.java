package com.learning.telusko.dao;

import com.learning.telusko.model.CustomerInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Currency;

public interface ICustomerDao extends CrudRepository<CustomerInfo,Integer> {

}
