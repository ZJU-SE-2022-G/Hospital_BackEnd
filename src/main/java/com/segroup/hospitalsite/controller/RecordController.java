package com.segroup.hospitalsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.IntroductionApp.entity.DoctorIntro;
import com.segroup.hospitalsite.IntroductionApp.service.IDoctorIntroService;
import com.segroup.hospitalsite.entity.Record;
import com.segroup.hospitalsite.entity.Workday;
import com.segroup.hospitalsite.mapper.DoctorIntroMapper;
import com.segroup.hospitalsite.service.IRecordService;
import com.segroup.hospitalsite.service.IWorkdayService;
import com.segroup.hospitalsite.utils.CommonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zheng
 * @since 2022-06-12
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private IRecordService iRecordService;

    @Autowired
    private IDoctorIntroService iDoctorIntroService;

    @Autowired
    private IWorkdayService iWorkdayService;

    @ApiOperation("获取所有预约信息")
    @RequestMapping(value = "/fetch-all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Record>> getAllPatientInfo()
    {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        List<Record> queryList = iRecordService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("获取指定用户预约信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Record> getRecordById(@PathVariable Long id)
    {
        Record record = iRecordService.getById(id);
        return CommonResult.success(record);
    }

    @ApiOperation("添加指定用户信息")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "uid", value = "用户id", required = true),
            @ApiImplicitParam(paramType = "query", name = "name", value = "病人姓名", required = true),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "病人性别", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "age", value = "病人年龄", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "pwd", value = "病人身份证", required = false, defaultValue = "-1"),
            @ApiImplicitParam(paramType = "query", name = "departmentName", value = "就诊科室名", required = true, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "docName", value = "医生姓名", required = true, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "orderedTime", value = "就诊日期", required = true, defaultValue = "null")
    })
    public CommonResult<String> insertRecord(
            @RequestParam Integer uid,
            @RequestParam String name,
            @RequestParam String sex,
            @RequestParam Integer age,
            @RequestParam String pwd,
            @RequestParam String departmentName,
            @RequestParam String docName,
            @RequestParam String orderedTime
    )
    {
        QueryWrapper<DoctorIntro> docWrapper = new QueryWrapper<>();
        docWrapper.eq("doc_name", docName);

        List<DoctorIntro> doctorIntroList = iDoctorIntroService.list(docWrapper);
        if(doctorIntroList == null)
        {
            return CommonResult.failed("不存在该医生");
        }
        String did = doctorIntroList.get(0).getDocId();
        System.out.println("did = "+ did);

        QueryWrapper<Workday> workWrapper = new QueryWrapper<>();
        workWrapper.eq("did", did);
        workWrapper.eq("work_time", orderedTime);
        Workday workday = iWorkdayService.getOne(workWrapper);
        if(workday == null)
        {
            return CommonResult.failed("不存在该工作日");
        }
        int serialNum = workday.increment();

        Record record = new Record();

        record.setUid(uid);
        record.setWid(workday.getWid());
        record.setDid(did);
        record.setSerialnumber(serialNum);
        record.setName(name);
        record.setAge(age);
        record.setPwd(pwd);
        record.setVisitData(orderedTime);
        record.setOrderData(LocalDateTime.now().toString());
        String state = workday.getState();
        if("可被预约的".equals(state)) {
            record.setState("成功");
        }
        else
        {
            record.setState("失败");
        }
        if(iRecordService.saveOrUpdate(record)) {
            return CommonResult.success("");
        }
        else
        {
            return CommonResult.failed();
        }

    }


    @ApiOperation("改变指定用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "rid", value = "预定号", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "uid", value = "用户id", required = true),
            @ApiImplicitParam(paramType = "query", name = "name", value = "病人姓名", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "病人性别", required = false, defaultValue = "男"),
            @ApiImplicitParam(paramType = "query", dataType = "int",name = "age", value = "病人年龄", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "pwd", value = "病人身份证", required = false, defaultValue = "-1"),
            @ApiImplicitParam(paramType = "query", name = "departmentName", value = "就诊科室名", required = true, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "docName", value = "医生姓名", required = true, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "orderedTime", value = "就诊日期", required = true, defaultValue = "null")
    })
    public CommonResult<String> updateRecord(
            @RequestParam Integer rid,
            @RequestParam Integer uid,
            @RequestParam String name,
            @RequestParam String sex,
            @RequestParam Integer age,
            @RequestParam String pwd,
            @RequestParam String departmentName,
            @RequestParam String docName,
            @RequestParam String orderedTime
    )
    {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.eq("rid", rid);
        List<Record> queryList = iRecordService.list(wrapper);
        Record record = queryList.get(0);

        QueryWrapper<DoctorIntro> docWrapper = new QueryWrapper<>();
        docWrapper.eq("doc_name", docName);

        List<DoctorIntro> doctorIntroList = iDoctorIntroService.list(docWrapper);
        if (doctorIntroList == null) {
            return CommonResult.failed("不存在该医生");
        }
        String newDid = doctorIntroList.get(0).getDocId();

        Workday iniWork = iWorkdayService.getById(record.getWid());
        // 原预约取消, 释放号源
        iniWork.decrement();

        QueryWrapper<Workday> workWrapper = new QueryWrapper<>();
        workWrapper.eq("did", newDid);
        workWrapper.eq("work_time", orderedTime);
        Workday newWork = iWorkdayService.getOne(workWrapper);
        // 新预约预订, 获得号源
        int serialNum = newWork.increment();

        record.setName(name);
        record.setSex(sex);
        record.setUid(uid);
        record.setWid(newWork.getWid());
        record.setDid(newDid);
        record.setSerialnumber(serialNum);
        record.setVisitData(orderedTime);
        record.setOrderData(LocalDateTime.now().toString());
        String state = newWork.getState();
        if("可被预约的".equals(state)) {
            record.setState("成功");
        }
        else
        {
            record.setState("失败");
        }
        if(iRecordService.saveOrUpdate(record)) {
            return CommonResult.success("");
        }
        else
        {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除指定预约信息")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "rid", value = "工作编号", required = true)
    public CommonResult<String> deletePatientInfoById(@RequestParam Integer rid) {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.eq("rid", rid);
        if (iRecordService.remove(wrapper)) {
            return CommonResult.success("");
        }
        else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除所有预约信息")
    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<String> deleteAllPatients()
    {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.ne("rid", "");
        if(iRecordService.remove(wrapper)) {
            return CommonResult.success("");
        }
        else {
            return CommonResult.failed();
        }
    }
}