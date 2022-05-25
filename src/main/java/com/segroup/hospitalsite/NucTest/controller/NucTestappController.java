package com.segroup.hospitalsite.NucTest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.NucTest.service.INucTestappService;
import com.segroup.hospitalsite.NucTest.entity.NucTestapp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 核酸检测预约表 前端控制器
 * </p>
 *
 * @author Tristan
 * @since 2022-05-25
 */
@RequestMapping("/nucTestApp")
@Controller
@Api(tags = "核酸检测数据接口")
public class NucTestappController {
    @Autowired
    private INucTestappService ntaService;

    @ApiOperation(value = "核酸检测预约界面", notes = "跳转至核酸检测预约界面")
    @GetMapping("/makeApp")
    public String Appointment(){
        return "app"; //需要换成实际的html页面，放到templates/中
    }

    @ApiOperation(value = "核酸检测查询界面", notes = "跳转至核酸检测预约界面")
    @GetMapping("/query")
    public String Query(){
        return "query"; //需要换成实际的html页面，放到templates/中
    }
    @ApiOperation(value = "修改核酸预约日期", notes = "根据身份证号修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "usr_id", value = "身份证号", required = true),
            @ApiImplicitParam(paramType = "path", name = "new_date", value = "新的预约时间", required = true)
    })
    @GetMapping("/update/{usr_id}/{new_date}")
    @ResponseBody
    public String updateRecord(@PathVariable String usr_id, @PathVariable String new_date){
        LocalDate t_new_date = LocalDate.parse(new_date);
        ntaService.updateByUsrId(usr_id, t_new_date);
        return "成功修改预约日期为: "+ new_date;
    }

    @ApiOperation(value = "取消核酸检测预约", notes = "根据身份证号取消核酸检测预约")
    @ApiImplicitParam(paramType = "path", name = "usr_id", value="身份证号", required = true)
    @GetMapping("delete/{usr_id}")
    @ResponseBody
    public String deleteRecord(@PathVariable String usr_id){
        QueryWrapper<NucTestapp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usr_id);
        boolean flag = ntaService.remove(wrapper);
        if(flag){
            return "取消成功";
        }
        else {
            return "取消失败";
        }
    }

    @ApiOperation(value = "核酸检测预约记录查询", notes = "根据身份证号查询核酸检测预约记录")
    @ApiImplicitParam(paramType = "path", name = "usr_id", value="身份证号", required = true)
    @GetMapping ("/query/{usr_id}")
    @ResponseBody
    public List<NucTestapp> queryRecord(@PathVariable String usr_id){
        QueryWrapper<NucTestapp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usr_id);
        return ntaService.list(wrapper);
    }

    @ApiOperation(value = "核酸预约记录新增", notes = "根据提交的预约信息增加记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "usrName", value="姓名", required = true),
            @ApiImplicitParam(paramType = "query", name = "usrId", value = "身份证号", required = true),
            @ApiImplicitParam(paramType = "query", name = "testType", value = "核酸检测类型0:单检1:混检", required = true),
            @ApiImplicitParam(paramType = "query", name = "testDate", value = "核酸检测时间", required = true)
    })
    @PostMapping ("/insert")
    @ResponseBody
    public String insertAppRecord(@RequestParam String usrName,
                                  @RequestParam String usrId,
                                  @RequestParam Integer testType,
                                  @RequestParam String testDate){
        NucTestapp ntaInfo = new NucTestapp();
        ntaInfo.setUsrName(usrName);
        ntaInfo.setUsrId(usrId);
        ntaInfo.setTestType(testType);
        ntaInfo.setTestDate(LocalDate.parse(testDate));
        ntaService.save(ntaInfo);
        return "success";
    }

}
