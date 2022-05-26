package com.segroup.hospitalsite.NucTest.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.NucTest.service.INucTestappService;
import com.segroup.hospitalsite.NucTest.entity.NucTestApp;
import com.segroup.hospitalsite.UserInfo.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
public class NucTestAppController{

    public static final int SUCCESS = 200;
    public static final int ID_EXIST_ERROR = 4001;
    public static final int ID_NOT_FOUND_ERROR = 4002;
    public static final int DATE_ILLEGAL_ERROR = 4003;
    public static final int REMOVE_ERROR = 4004;
    public static final int INSERT_ERROR = 4005;
    public static final int AFTER_DATE_ERROR = 4006;
    @Autowired
    private INucTestappService ntaService;

    @ApiOperation(value = "核酸检测预约界面", notes = "跳转至核酸检测预约界面")
    @GetMapping("/makeApp")
    public String Appointment(){
        return "nucApp"; //需要换成实际的html页面，放到templates/中
    }

    @ApiOperation(value = "核酸检测查询界面", notes = "跳转至核酸检测预约界面")
    @GetMapping("/query")
    public String Query(){
        return "nucQuery"; //需要换成实际的html页面，放到templates/中
    }

    @ApiOperation(value = "核酸检测修改日期界面", notes = "跳转至核酸检测修改日期界面")
    @GetMapping("/modify")
    public String Modify(){
        return "nucModify"; //需要换成实际的html页面，放到templates/中
    }

    @ApiOperation(value = "取消核酸检测预约界面", notes = "跳转至取消核酸检测预约界面")
    @GetMapping("/delete")
    public String Delete(){
        return "nucDelete"; //需要换成实际的html页面，放到templates/中
    }
    @ApiOperation(value = "修改核酸预约日期", notes = "根据身份证号修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "usr_id", value = "身份证号", required = true),
            @ApiImplicitParam(paramType = "path", name = "new_date", value = "新的预约时间", required = true)
    })
    @GetMapping("/update/{usr_id}/{new_date}")
    @ResponseBody
    public JsonResult<NucTestApp> updateRecord(@PathVariable String usr_id, @PathVariable String new_date){
        JsonResult<NucTestApp> result;
        QueryWrapper<NucTestApp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usr_id);
        List<NucTestApp> queryList = ntaService.list(wrapper);
        LocalDate t_new_date;
        try{
            t_new_date = LocalDate.parse(new_date);
        }
        catch (DateTimeParseException e)
        {
            result = new JsonResult<>(DATE_ILLEGAL_ERROR, "非法的日期格式，应为YYYY-mm-dd");
            return result;
        }
        if(queryList.size() == 0) {
            result = new JsonResult<>(ID_NOT_FOUND_ERROR, "该用户没有核酸检测预约记录");
            return result;
        }
        NucTestApp ntaInfo = queryList.get(0);
        ntaInfo.setTestDate(t_new_date);
        LocalDate now_date = LocalDate.now();
        if(now_date.isAfter(t_new_date)){
            result = new JsonResult<>(AFTER_DATE_ERROR, "修改后的日期不能早于当前日期！");
            return result;
        }
        ntaService.updateByUsrId(usr_id, new_date);
        result = new JsonResult<>(SUCCESS, "修改预约日期至: "+new_date);
        result.setData(ntaInfo);
        return result;
    }

    @ApiOperation(value = "取消核酸检测预约", notes = "根据身份证号取消核酸检测预约")
    @ApiImplicitParam(paramType = "path", name = "usr_id", value="身份证号", required = true)
    @GetMapping("delete/{usr_id}")
    @ResponseBody
    public JsonResult<Void>  deleteRecord(@PathVariable String usr_id){
        JsonResult<Void> result;
        QueryWrapper<NucTestApp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usr_id);
        List<NucTestApp> queryList = ntaService.list(wrapper);
        if(queryList.size() == 0){
            result = new JsonResult<>(ID_NOT_FOUND_ERROR, "该用户没有核酸检测预约记录");
            return result;
        }
        boolean flag = ntaService.remove(wrapper);
        if(flag){
            result = new JsonResult<>(SUCCESS, "取消预约成功！");
        }
        else{
            result = new JsonResult<>(REMOVE_ERROR, "取消预约失败！");
        }
        return result;
    }

    @ApiOperation(value = "核酸检测预约记录查询", notes = "根据身份证号查询核酸检测预约记录")
    @ApiImplicitParam(paramType = "path", name = "usr_id", value="身份证号", required = true)
    @GetMapping ("/query/{usr_id}")
    @ResponseBody
    public JsonResult<NucTestApp> queryRecord(@PathVariable String usr_id){
        JsonResult<NucTestApp> result;
        QueryWrapper<NucTestApp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usr_id);
        List<NucTestApp> queryList = ntaService.list(wrapper);
        if(queryList.size() == 0){
            result = new JsonResult<>(ID_NOT_FOUND_ERROR, "该用户没有核酸检测预约记录");
        }
        else{
            result = new JsonResult<>(SUCCESS, "查询成功，返回用户预约记录");
            result.setData(queryList.get(0));
        }
        return result;
    }

    @ApiOperation(value = "核酸预约记录新增", notes = "根据提交的预约信息增加记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "usrName", value="姓名", required = true),
            @ApiImplicitParam(paramType = "query", name = "usrId", value = "身份证号", required = true),
            @ApiImplicitParam(paramType = "query", name = "testType", value = "核酸检测类型:单检/混检", required = true),
            @ApiImplicitParam(paramType = "query", name = "testDate", value = "核酸检测时间", required = true)
    })
    @PostMapping ("/insert")
    @ResponseBody
    public JsonResult<NucTestApp> insertAppRecord(@RequestParam String usrName,
                                  @RequestParam String usrId,
                                  @RequestParam String testType,
                                  @RequestParam String testDate){
        JsonResult<NucTestApp> result;
        QueryWrapper<NucTestApp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usrId);
        List<NucTestApp> queryList = ntaService.list(wrapper);
        NucTestApp ntaInfo = new NucTestApp();
        ntaInfo.setUsrName(usrName);
        ntaInfo.setUsrId(usrId);
        ntaInfo.setTestType(testType);
        ntaInfo.setTestDate(LocalDate.parse(testDate));
        if(queryList.size()!=0){
            result = new JsonResult<>(ID_EXIST_ERROR, "该用户已经预约过，不能重复预约");
            result.setData(ntaInfo);
            return result;
        }
        boolean flag = ntaService.save(ntaInfo);
        if(flag){
            result = new JsonResult<>(SUCCESS, "预约成功,返回预约信息");
            result.setData(ntaInfo);
        }
        else{
            result = new JsonResult<>(INSERT_ERROR, "插入记录失败！");
        }
        return result;
    }

}
