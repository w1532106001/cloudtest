package com.whc.ribbonclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @author whc
 * @date 2020/11/9
 * @description
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private RestTemplate template;


    @HystrixCommand(fallbackMethod = "helloFallback",commandKey = "helloKey")
    @GetMapping("/ribbon")
    public String ribbonTest(){
        return template.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    public String helloFallback(){
        return "error";
    }
}
