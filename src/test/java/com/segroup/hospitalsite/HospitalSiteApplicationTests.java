package com.segroup.hospitalsite;

import com.segroup.hospitalsite.NucTest.entity.NucTestApp;
import com.segroup.hospitalsite.mapper.NucTestappMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.List;

@EnableSwagger2
@SpringBootTest
class HospitalSiteApplicationTests {

    @Resource
    private NucTestappMapper userMapper;
    @Test
    void contextLoads() {

        List<NucTestApp> queryList = userMapper.selectList(null);
        for(NucTestApp nta : queryList){
            System.out.println("身份证号："+nta.getUsrId());
            System.out.println("姓名："+ nta.getUsrName());
            System.out.println("预约日期："+nta.getTestDate());
        }
    }

}
