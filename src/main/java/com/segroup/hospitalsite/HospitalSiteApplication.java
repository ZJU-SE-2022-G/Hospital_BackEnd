package com.segroup.hospitalsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zheng
 */
@SpringBootApplication
@MapperScan("com.segroup.hospitalsite.UserInfo.mapper")
public class HospitalSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalSiteApplication.class, args);
    }

}
