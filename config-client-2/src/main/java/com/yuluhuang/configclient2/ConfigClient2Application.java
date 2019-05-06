package com.yuluhuang.configclient2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class ConfigClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient2Application.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/foo")
    public String hi() {
        return port;
    }
}
