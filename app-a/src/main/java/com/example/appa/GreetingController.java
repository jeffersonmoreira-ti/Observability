package com.example.appa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Order Details, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private RestTemplate restTemplate;
    public String response;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {
        String url = "http://localhost:8092/customer?customerName="+name;
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            System.out.println(String.format(template, response));
            return new Greeting(counter.incrementAndGet(), String.format(template, response));
        }catch (Exception ex){
           return new Greeting(counter.incrementAndGet(), String.format(template, ex.getMessage()));
        }
    }

    @RequestMapping("/greetingwithsleep")
    public Greeting greetingSleep(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "sleepTime", defaultValue = "0") int sleepTime) throws Exception {
        String url = "http://localhost:8092/customerwithsleep?customerName="+name+"&sleepTime="+sleepTime;
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            System.out.println(String.format(template, response));
            return new Greeting(counter.incrementAndGet(), String.format(template, response));
        }catch (Exception ex){
            return new Greeting(counter.incrementAndGet(), String.format(template, ex.getMessage()));
        }
    }

}
