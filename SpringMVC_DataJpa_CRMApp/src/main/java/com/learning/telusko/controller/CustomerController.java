package com.learning.telusko.controller;

import com.learning.telusko.model.CustomerInfo;
import com.learning.telusko.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService service;

    @GetMapping("/clist")
    public String fetchCustomerData(Model model){
        List<CustomerInfo> customerInfoList=service.getCustomers();
        customerInfoList.forEach(c-> System.out.println(c));
        model.addAttribute("customers",customerInfoList);
        return "customerlist";
    }
}
