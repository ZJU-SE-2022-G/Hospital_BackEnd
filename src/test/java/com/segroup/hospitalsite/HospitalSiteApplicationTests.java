package com.segroup.hospitalsite;

import com.segroup.hospitalsite.NucTest.mapper.NucTestAppMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@EnableSwagger2
@SpringBootTest
class HospitalSiteApplicationTests {

    @Resource
    private NucTestAppMapper userMapper;
    @Test
    void contextLoads() {
        userMapper.selectList(null).forEach(System.out::println);
    }

}
