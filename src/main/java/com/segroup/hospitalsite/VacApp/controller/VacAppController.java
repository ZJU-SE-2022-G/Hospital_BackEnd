package com.segroup.hospitalsite.VacApp.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.UserInfo.utils.JsonResult;
import com.segroup.hospitalsite.VacApp.entity.VacApp;
import com.segroup.hospitalsite.VacApp.service.IVacAppService;
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
 * 疫苗预约接种表 前端控制器
 * </p>
 *
 * @author Tristan
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/vacApp")
public class VacAppController {
    public static final int SUCCESS = 200;
    public static final int ID_EXIST_ERROR = 4001;
    public static final int ID_NOT_FOUND_ERROR = 4002;
    public static final int DATE_ILLEGAL_ERROR = 4003;
    public static final int REMOVE_ERROR = 4004;
    public static final int INSERT_ERROR = 4005;
    public static final int AFTER_DATE_ERROR = 4006;

    @Autowired
    private IVacAppService vcService;

    @ApiOperation(value = "新冠疫苗预约界面", notes = "跳转至新冠疫苗预约界面")
    @GetMapping("/makeApp")
    public String Appointment(){
        return "vacApp"; //需要换成实际的html页面，放到templates/中
    }

    @ApiOperation(value = "新冠疫苗查询界面", notes = "跳转至新冠疫苗查询界面")
    @GetMapping("/query")
    public String Query() { return "vacQuery";}
    @ApiOperation(value = "新冠疫苗修改预约日期界面", notes = "跳转至新冠疫苗修改预约日期界面")
    @GetMapping("/modify")
    public String Modify() { return "vacModify";}
    @ApiOperation(value = "取消新冠疫苗预约界面", notes = "跳转至取消新冠疫苗预约界面")
    @GetMapping("/delete")
    public String Delete() { return "vacDelete";}

    @ApiOperation(value = "新冠疫苗预约", notes = "新增新冠疫苗预约记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "usrName", value="姓名", required = true),
            @ApiImplicitParam(paramType = "query", name = "usrId", value = "身份证号", required = true),
            @ApiImplicitParam(paramType = "query", name = "age", value = "年龄", required = true),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "性别", required = true),
            @ApiImplicitParam(paramType = "query", name = "vacNum", value = "预约接种针数", required = true),
            @ApiImplicitParam(paramType = "query", name = "vacDate", value = "疫苗预约时间", required = true)
    })
    @PostMapping("/insert")
    @ResponseBody
    public JsonResult<VacApp> insertVacRecord(@RequestParam String usrName,
                                              @RequestParam String usrId,
                                              @RequestParam Integer age,
                                              @RequestParam String sex,
                                              @RequestParam Integer vacNum,
                                              @RequestParam String vacDate){
        JsonResult<VacApp> result;
        QueryWrapper<VacApp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usrId);
        List<VacApp> queryList = vcService.list(wrapper);
        boolean flag = true;
        VacApp vcInfo = new VacApp();
        vcInfo.setUsrName(usrName);
        vcInfo.setUsrId(usrId);
        vcInfo.setAge(age);
        vcInfo.setSex(sex);
        vcInfo.setVacNum(vacNum);
        vcInfo.setVacDate(LocalDate.parse(vacDate));
        if(queryList.size()!=0){
            result = new JsonResult<>(ID_EXIST_ERROR, "该用户已经预约过，不能重复预约");
            result.setData(vcInfo);
            return result;
        }
        flag = vcService.save(vcInfo);
        if(flag){
            result = new JsonResult<>(SUCCESS, "预约成功,返回预约信息");
            result.setData(vcInfo);
        }
        else {
            result = new JsonResult<>(INSERT_ERROR, "插入记录失败！");
        }
        return result;
    }

    @ApiOperation(value = "取消新冠疫苗预约", notes = "根据身份证号取消新冠疫苗预约")
    @ApiImplicitParam(paramType = "path", name = "usr_id", value="身份证号", required = true)
    @GetMapping("delete/{usr_id}")
    @ResponseBody
    public JsonResult<Void>  deleteRecord(@PathVariable String usr_id){
        JsonResult<Void> result;
        QueryWrapper<VacApp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usr_id);
        List queryList = vcService.list(wrapper);
        if(queryList.size() == 0){
            result = new JsonResult<>(ID_NOT_FOUND_ERROR, "该用户没有核酸检测预约记录");
            return result;
        }
        boolean flag = vcService.remove(wrapper);
        if(flag){
            result = new JsonResult<>(SUCCESS, "取消预约成功！");
        }
        else{
            result = new JsonResult<>(REMOVE_ERROR, "取消预约失败！");
        }
        return result;
    }

    @ApiOperation(value = "修改新冠疫苗预约日期", notes = "根据身份证号修改预约日期")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "usr_id", value = "身份证号", required = true),
            @ApiImplicitParam(paramType = "path", name = "new_date", value = "新的预约时间", required = true)
    })
    @GetMapping("/update/{usr_id}/{new_date}")
    @ResponseBody
    public JsonResult<VacApp> updateRecord(@PathVariable String usr_id, @PathVariable String new_date){
        JsonResult<VacApp> result;
        QueryWrapper<VacApp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usr_id);
        List<VacApp> queryList = vcService.list(wrapper);
        LocalDate t_new_date;
        try{
            t_new_date = LocalDate.parse(new_date);
        }
        catch (DateTimeParseException e) {
            result = new JsonResult<>(DATE_ILLEGAL_ERROR, "非法的日期格式，应为YYYY-mm-dd");
            return result;
        }
        if(queryList.size() == 0) {
            result = new JsonResult<>(ID_NOT_FOUND_ERROR, "该用户没有核酸检测预约记录");
            return result;
        }
        VacApp vcInfo = queryList.get(0);
        vcInfo.setVacDate(t_new_date);
        LocalDate now_date = LocalDate.now();
        if(now_date.isAfter(t_new_date)){
            result = new JsonResult<>(AFTER_DATE_ERROR, "修改后的日期不能早于当前日期！");
            return result;
        }
        result = new JsonResult<>(SUCCESS, "修改预约日期至："+new_date);
        result.setData(vcInfo);
        vcService.updateByUsrId(usr_id, new_date);
        return result;
    }
    @ApiOperation(value = "新冠疫苗预约记录查询", notes = "根据身份证号查询新冠疫苗预约记录")
    @ApiImplicitParam(paramType = "path", name = "usr_id", value="身份证号", required = true)
    @GetMapping ("/query/{usr_id}")
    @ResponseBody
    public JsonResult<VacApp> queryRecord(@PathVariable String usr_id){
        JsonResult<VacApp> result;
        QueryWrapper<VacApp> wrapper = new QueryWrapper<>();
        wrapper.eq("usr_id", usr_id);
        List<VacApp> queryList = vcService.list(wrapper);
        if(queryList.size() == 0){
            result = new JsonResult<>(ID_NOT_FOUND_ERROR, "该用户没有核酸检测预约记录");
        }
        else{
            result = new JsonResult<>(SUCCESS, "查询成功，返回用户预约记录");
            result.setData(queryList.get(0));
        }
        return result;
    }

}
