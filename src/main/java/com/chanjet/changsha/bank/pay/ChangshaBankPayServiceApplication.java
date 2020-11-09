package com.chanjet.changsha.bank.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zsc
 */
@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class ChangshaBankPayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChangshaBankPayServiceApplication.class, args);
    }

}
