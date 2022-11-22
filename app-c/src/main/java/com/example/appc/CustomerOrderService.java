package com.example.appc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class CustomerOrderService {

    @Autowired
    private CustomerOrderRepository repository;

    private static final String order = "{\n" +
            "   id:125b445gf,\n" +
            "   customerId:456897,\n" +
            "   customerName:%s,\n" +
            "   productName:Honda Civic,\n" +
            "   quantity:1,\n" +
            "   unityPrice:280000.00,\n" +
            "   totalPrice:280000.00\n" +
            "}";

    public String findAll(){
        repository.findAll();
        return "Jeff";
    }

    public String findByName(String name) throws InterruptedException {
        System.out.println(name);
        repository.findAll();
        return String.format(order,name);
    }

    public String findByName(String name, int SleepTime) throws InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.now();
        System.out.println(" --------------------- Pedido --------------------- ");
        System.out.println("Start: "+dtf.format(start));

        repository.findAll();

        Thread.sleep(SleepTime);

        LocalDateTime finish = LocalDateTime.now();
        System.out.println("Finish: "+dtf.format(finish));

        long diff = ChronoUnit.SECONDS.between(start, finish);

        System.out.println(name);
        System.out.println("Segundos decorridos: "+diff);
        return String.format(order,name);
    }

}
