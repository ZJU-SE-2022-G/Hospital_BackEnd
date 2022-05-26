package com.segroup.hospitalsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 请不要修改 MapperScan，把mapper放到mapper目录下
 * @author zheng
 */
@SpringBootApplication
@MapperScan("com.segroup.hospitalsite.mapper")
public class HospitalSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalSiteApplication.class, args);
    }

}
