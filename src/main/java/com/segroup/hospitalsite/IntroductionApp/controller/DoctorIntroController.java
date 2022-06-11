package com.segroup.hospitalsite.IntroductionApp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.IntroductionApp.entity.DoctorIntro;
import com.segroup.hospitalsite.IntroductionApp.service.IDoctorIntroService;
import com.segroup.hospitalsite.utils.CommonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 医生信息介绍表 前端控制器
 * </p>
 *
 * @author yuguoyao
 * @since 2022-06-05
 */
@Controller
@RequestMapping("/doctorIntro")
public class DoctorIntroController {

    @Autowired
    private IDoctorIntroService iDoctorIntroService;

    @ApiOperation("获取指定名医介绍")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParam(paramType = "path", name = "id",
            value = "医生编号", required = true, defaultValue = "test")
    public CommonResult<List<DoctorIntro>> getDoctorInfoById(@PathVariable String id)
    {
        QueryWrapper<DoctorIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("doc_id", id);
        List<DoctorIntro> queryList = iDoctorIntroService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("获取所有名医介绍")
    @RequestMapping(value = "/fetch-all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DoctorIntro>> getAllDoctorsInfo()
    {
        QueryWrapper<DoctorIntro> wrapper = new QueryWrapper<>();
        List<DoctorIntro> queryList = iDoctorIntroService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("导入名医介绍")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "doc_id", value = "医生编号", required = true),
            @ApiImplicitParam(paramType = "query", name = "doc_name", value = "医生姓名", required = true),
            @ApiImplicitParam(paramType = "query", name = "doc_sex", value = "医生性别", required = true),
            @ApiImplicitParam(paramType = "query", name = "doc_age", value = "医生年龄", required = true),
            @ApiImplicitParam(paramType = "query", name = "department_name", value = "所在科室", required = true),
            @ApiImplicitParam(paramType = "query", name = "intro_detail", value = "详细介绍", required = true)
    })
    public CommonResult<String> insertDoctorInfo(
            @RequestParam String doc_id,
            @RequestParam String doc_name,
            @RequestParam String doc_sex,
            @RequestParam Integer doc_age,
            @RequestParam String department_name,
            @RequestParam String intro_detail
    )
    {
        DoctorIntro doc_info = new DoctorIntro(doc_id, doc_name, doc_sex, doc_age, department_name, intro_detail);
        if (iDoctorIntroService.saveOrUpdate(doc_info))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

    @ApiOperation("修改名医介绍")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "doc_id", value = "医生编号", required = true),
            @ApiImplicitParam(paramType = "query", name = "doc_name", value = "医生姓名", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "doc_sex", value = "医生性别", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "doc_age", value = "医生年龄", required = false, defaultValue = "-1"),
            @ApiImplicitParam(paramType = "query", name = "department_name", value = "所在科室", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "intro_detail", value = "详细介绍", required = false, defaultValue = "null")
    })
    public CommonResult<String> updateDoctorInfoById(
            @RequestParam String doc_id,
            @RequestParam String doc_name,
            @RequestParam String doc_sex,
            @RequestParam Integer doc_age,
            @RequestParam String department_name,
            @RequestParam String intro_detail
    )
    {
        QueryWrapper<DoctorIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("doc_id", doc_id);
        List<DoctorIntro> queryList = iDoctorIntroService.list(wrapper);

        DoctorIntro doc_info = queryList.get(0);
        if (!Objects.equals(doc_name, "null"))
            doc_info.setDocName(doc_name);
        if (!Objects.equals(doc_sex, "null"))
            doc_info.setSex(doc_sex);
        if (!Objects.equals(doc_age, -1))
            doc_info.setAge(doc_age);
        if (!Objects.equals(department_name, "null"))
            doc_info.setDepartmantName(department_name);
        if (!Objects.equals(intro_detail, "null"))
            doc_info.setDocDetail(intro_detail);

        if (iDoctorIntroService.saveOrUpdate(doc_info)) {
            return CommonResult.success("");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除指定名医介绍")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiImplicitParam(paramType = "query", name = "doc_id", value = "医生编号", required = true)
    public CommonResult<String> deleteDoctorsInfoById(@RequestParam String doc_id)
    {
        QueryWrapper<DoctorIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("doc_id", doc_id);
        if(iDoctorIntroService.remove(wrapper))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

    @ApiOperation("删除所有名医介绍")
    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<String> deleteAllDoctorsInfo()
    {
        QueryWrapper<DoctorIntro> wrapper = new QueryWrapper<>();
        wrapper.ne("doc_id", "");
        if(iDoctorIntroService.remove(wrapper))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

}
