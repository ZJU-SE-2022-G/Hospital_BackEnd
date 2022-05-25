package com.segroup.hospitalsite.UserInfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class HospitalApplicationTests {

    @Autowired // 自动装配
    private DataSource dataSource;

    // 测试连接
    @Test
    void getConnection() throws SQLException{
        System.out.println(dataSource.getConnection());
    }


    @Test
    void contextLoads() {
    }

}
