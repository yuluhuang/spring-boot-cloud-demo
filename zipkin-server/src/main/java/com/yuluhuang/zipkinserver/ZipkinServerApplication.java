package com.yuluhuang.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import zipkin.storage.mysql.MySQLStorage;
import zipkin2.server.internal.EnableZipkinServer;

import javax.sql.DataSource;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinServerApplication {
    @Bean
    public MySQLStorage mySQLStorage(DataSource dataSource) {
        return MySQLStorage.builder().datasource(dataSource).executor(Runnable::run).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }

}
