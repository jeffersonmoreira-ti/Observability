package com.example.appb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    private static final String order = "{orders:%s:%s}";
    public String response;


    public String findAll(){
        repository.findAll();
        return "Jeff";
    }

    public String findByName(String name) throws InterruptedException {
        System.out.println(name);
        repository.findAll();

        String url = "http://localhost:8095/order?customerName="+name;
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

        return String.format(order,name,response);
    }

    public String findByName(String name, int SleepTime) throws InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.now();
        System.out.println(" --------------------- Cliente --------------------- ");
        System.out.println("Start: "+dtf.format(start));

        repository.findAll();

        String url = "http://localhost:8095/order?customerName="+name;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();


        Thread.sleep(SleepTime);

        LocalDateTime finish = LocalDateTime.now();
        System.out.println("Finish: "+dtf.format(finish));

        long diff = ChronoUnit.SECONDS.between(start, finish);

        System.out.println(name);
        System.out.println("Segundos decorridos: "+diff);
        return String.format(order,name,response);
    }

}
