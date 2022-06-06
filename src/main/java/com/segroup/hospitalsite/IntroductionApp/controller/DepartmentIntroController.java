package com.segroup.hospitalsite.IntroductionApp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.IntroductionApp.entity.DepartmentIntro;
import com.segroup.hospitalsite.IntroductionApp.entity.DoctorIntro;
import com.segroup.hospitalsite.IntroductionApp.service.IDepartmentIntroService;
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
 * 科室信息介绍表 前端控制器
 * </p>
 *
 * @author yuguoyao
 * @since 2022-06-06
 */
@Controller
@RequestMapping("/departmentIntro")
public class DepartmentIntroController {

    @Autowired
    private IDepartmentIntroService iDepartmentIntroService;

    @ApiOperation("获取指定科室介绍")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParam(paramType = "path", name = "id",
            value = "科室编号", required = true, defaultValue = "test")
    public CommonResult<List<DepartmentIntro>> getDoctorInfoById(@PathVariable String id)
    {
        QueryWrapper<DepartmentIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        List<DepartmentIntro> queryList = iDepartmentIntroService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("获取所有科室介绍")
    @RequestMapping(value = "/fetch-all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DepartmentIntro>> getAllDepartmentsInfo()
    {
        QueryWrapper<DepartmentIntro> wrapper = new QueryWrapper<>();
        List<DepartmentIntro> queryList = iDepartmentIntroService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("导入科室介绍")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "department_id", value = "科室编号", required = true),
            @ApiImplicitParam(paramType = "query", name = "department_name", value = "科室名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "intro_detail", value = "详细介绍", required = true)
    })
    public CommonResult<String> insertDepartmentInfo(
            @RequestParam String department_id,
            @RequestParam String department_name,
            @RequestParam String intro_detail
    )
    {
        DepartmentIntro doc_info = new DepartmentIntro(department_id, department_name, intro_detail);
        if (iDepartmentIntroService.saveOrUpdate(doc_info))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

    @ApiOperation("修改指定科室介绍")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "department_id", value = "科室编号", required = true),
            @ApiImplicitParam(paramType = "query", name = "department_name", value = "科室名称", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "intro_detail", value = "详细介绍", required = false, defaultValue = "null")
    })
    public CommonResult<String> updateDepartmentInfoById(
            @RequestParam String department_id,
            @RequestParam String department_name,
            @RequestParam String intro_detail
    )
    {
        QueryWrapper<DepartmentIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("id", department_id);
        List<DepartmentIntro> queryList = iDepartmentIntroService.list(wrapper);

        DepartmentIntro doc_info = queryList.get(0);
        if (!Objects.equals(department_name, "null"))
            doc_info.setName(department_name);
        if (!Objects.equals(intro_detail, "null"))
            doc_info.setDetail(intro_detail);

        if (iDepartmentIntroService.saveOrUpdate(doc_info))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

    @ApiOperation("删除指定科室介绍")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiImplicitParam(paramType = "query", name = "department_id", value = "科室编号", required = true)
    public CommonResult<String> deleteDepartmentsInfoById(@RequestParam String department_id)
    {
        QueryWrapper<DepartmentIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("id", department_id);
        if(iDepartmentIntroService.remove(wrapper))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

    @ApiOperation("删除所有科室介绍")
    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<String> deleteAllDepartmentsInfo()
    {
        QueryWrapper<DepartmentIntro> wrapper = new QueryWrapper<>();
        if(iDepartmentIntroService.remove(wrapper))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

}
