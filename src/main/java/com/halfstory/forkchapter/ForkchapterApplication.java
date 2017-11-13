package com.halfstory.forkchapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xu
 * @date 2017/10/11
 */
@EnableSwagger2
@ServletComponentScan
@SpringBootApplication
public class ForkchapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForkchapterApplication.class, args);
    }
}
