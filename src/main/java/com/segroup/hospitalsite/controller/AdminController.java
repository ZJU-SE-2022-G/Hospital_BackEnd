package com.segroup.hospitalsite.controller;

import com.segroup.hospitalsite.entity.Admin;
import com.segroup.hospitalsite.service.IAdminService;
import com.segroup.hospitalsite.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zheng
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private IAdminService iAdminService;

    @ApiOperation(value="获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo()
    {
        Map<String, Object> data = new HashMap<>();
        // TODO
//        data.put("username", iAdminService.getUsername());

        return CommonResult.success(data);
    }

    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Admin> getItem(@PathVariable Long id)
    {
        Admin admin = iAdminService.getById(id);
        return CommonResult.success(admin);
    }



//    @ApiOperation(value="管理员注册")
//    @RequestMapping(value="/register", method = RequestMethod.POST)
//    @ResponseBody
//    public void register(@Validated @ResponseBody )
}
