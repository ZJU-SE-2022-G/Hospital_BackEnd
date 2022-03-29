package com.segroup.hospitalsite.controller;

import com.segroup.hospitalsite.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * @author zheng
 * @date 2022-03-30
 */
@RestController
@Api(tags = "打招呼接口")
public class HelloController {
    @GetMapping(value="/hello")
    public User getUser()
    {
        return new User();
    }
    @ApiOperation(value="打招呼",notes="可以指定参数的API")
    @PostMapping("/param")
    public String hello(@ApiParam("用户名") String name){
        return "hello" + name;
    }
}
