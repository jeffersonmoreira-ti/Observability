package com.example.appb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "/customer")
    public String getCustomerName(@RequestParam(value = "customerName", defaultValue = "Customer Name") String customerName) throws InterruptedException {
        return customerService.findByName(customerName);
    }

    @RequestMapping(path = "/customerwithsleep")
    public String getCustomerName(@RequestParam(value = "customerName", defaultValue = "Customer Name")String customerName,@RequestParam(value = "sleepTime", defaultValue = "0") int sleepTime) throws InterruptedException {
        return customerService.findByName(customerName,sleepTime);
    }

}
