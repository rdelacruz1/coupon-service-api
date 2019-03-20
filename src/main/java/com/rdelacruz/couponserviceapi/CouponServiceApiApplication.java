package com.rdelacruz.couponserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CouponServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CouponServiceApiApplication.class);
        springApplication.addInitializers(new EmbeddedCassandraInitializer());
        springApplication.run(args);

    }

}
