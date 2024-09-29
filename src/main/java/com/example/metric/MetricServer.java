package com.example.metric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MetricServer {

  public static void main(String[] args) {
    SpringApplication.run(MetricServer.class, args);
  }

}
