package com.halfstory.forkchapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xu
 * @date 2017/10/11
 */
@EnableSwagger2
@ServletComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.halfstory")
public class ForkchapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForkchapterApplication.class, args);
    }
}
