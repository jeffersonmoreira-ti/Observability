package com.example.appc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;


    @RequestMapping(path = "/order")
    public String getOrder(@RequestParam(value = "customerName", defaultValue = "Customer Name") String customerName) throws InterruptedException {
        return customerOrderService.findByName(customerName);
    }

    @RequestMapping(path = "/orderwithsleep")
    public String getOrderName(@RequestParam(value = "customerName", defaultValue = "Customer Name")String customerName,@RequestParam(value = "sleepTime", defaultValue = "0") int sleepTime) throws InterruptedException {
        return customerOrderService.findByName(customerName,sleepTime);
    }

}
