package com.leo.cloudhubserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@MapperScan("com.leo.cloudhubserver.repository.mapper")
@SpringBootApplication
public class CloudHubServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudHubServerApplication.class, args);
    }
    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
}
