package com.rdelacruz.couponserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CouponServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CouponServiceApiApplication.class);
        springApplication.addInitializers(new EmbeddedCassandraInitializer());
        springApplication.run(args);

    }

}
