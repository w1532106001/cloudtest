package com.whc.client.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
    private Registration registration; // 服务注册
    @Autowired
    private DiscoveryClient client; // 服务发现类


    @GetMapping("/hello")
    public String index() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(registration.getHost()+"---"+registration.getPort()+"---"+registration.getServiceId());
        return "hello";
    }
    ServiceInstance getServiceInstance() {
        ServiceInstance serviceInstance = null;
        List<ServiceInstance> serviceInstanceList = client.getInstances(registration.getServiceId());
        for (ServiceInstance instance : serviceInstanceList) {
            if (instance.getPort() == 8181) {
                serviceInstance = instance;
                break;
            }
        }
        return serviceInstance;
    }
}
