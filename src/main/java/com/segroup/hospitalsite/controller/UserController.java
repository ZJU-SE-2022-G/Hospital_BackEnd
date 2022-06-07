package com.segroup.hospitalsite.controller;

import com.segroup.hospitalsite.entity.User;
import com.segroup.hospitalsite.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zheng
 * @since 2022-03-30
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService demoService;

    @ApiOperation("删除指定id的用户")
    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteUser(@PathVariable("id") Long id){
        return demoService.removeById(id);
    }

    @ApiOperation("返回所有用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getList()
    {
        List<User> users = demoService.list();
        return users;
    }



}
