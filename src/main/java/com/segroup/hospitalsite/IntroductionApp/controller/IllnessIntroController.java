package com.segroup.hospitalsite.IntroductionApp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.IntroductionApp.entity.IllnessIntro;
import com.segroup.hospitalsite.IntroductionApp.service.IIllnessIntroService;
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
 * 病情信息介绍表 前端控制器
 * </p>
 *
 * @author yuguoyao
 * @since 2022-06-06
 */
@Controller
@RequestMapping("/illnessIntro")
public class IllnessIntroController {

    @Autowired
    private IIllnessIntroService iIllnessIntroService;

    @ApiOperation("获取指定病情介绍")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParam(paramType = "path", name = "id",
            value = "病情编号", required = true, defaultValue = "test")
    public CommonResult<List<IllnessIntro>> getIllnessInfoById(@PathVariable String id)
    {
        QueryWrapper<IllnessIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        List<IllnessIntro> queryList = iIllnessIntroService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("获取所有病情介绍")
    @RequestMapping(value = "/fetch-all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<IllnessIntro>> getAllIllnessesInfo()
    {
        QueryWrapper<IllnessIntro> wrapper = new QueryWrapper<>();
        List<IllnessIntro> queryList = iIllnessIntroService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("导入病情介绍")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "illness_id", value = "病情编号", required = true),
            @ApiImplicitParam(paramType = "query", name = "illness_name", value = "病情名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "intro_detail", value = "详细介绍", required = true)
    })
    public CommonResult<String> insertIllnessInfo(
            @RequestParam String illness_id,
            @RequestParam String illness_name,
            @RequestParam String intro_detail
    )
    {
        IllnessIntro doc_info = new IllnessIntro(illness_id, illness_name, intro_detail);
        if (iIllnessIntroService.saveOrUpdate(doc_info))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

    @ApiOperation("修改指定病情介绍")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "illness_id", value = "病情编号", required = true),
            @ApiImplicitParam(paramType = "query", name = "illness_name", value = "病情名称", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "intro_detail", value = "详细介绍", required = false, defaultValue = "null")
    })
    public CommonResult<String> updateIllnessInfoById(
            @RequestParam String illness_id,
            @RequestParam String illness_name,
            @RequestParam String intro_detail
    )
    {
        QueryWrapper<IllnessIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("id", illness_id);
        List<IllnessIntro> queryList = iIllnessIntroService.list(wrapper);

        IllnessIntro doc_info = queryList.get(0);
        if (!Objects.equals(illness_name, "null"))
            doc_info.setName(illness_name);
        if (!Objects.equals(intro_detail, "null"))
            doc_info.setDetail(intro_detail);

        if (iIllnessIntroService.saveOrUpdate(doc_info))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

    @ApiOperation("删除指定病情介绍")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiImplicitParam(paramType = "query", name = "illness_id", value = "病情编号", required = true)
    public CommonResult<String> deleteIllnessesInfoById(@RequestParam String illness_id)
    {
        QueryWrapper<IllnessIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("id", illness_id);
        if(iIllnessIntroService.remove(wrapper))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

    @ApiOperation("删除所有病情介绍")
    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<String> deleteAllIllnessesInfo()
    {
        QueryWrapper<IllnessIntro> wrapper = new QueryWrapper<>();
        if(iIllnessIntroService.remove(wrapper))
            return CommonResult.success("");
        else
            return CommonResult.failed();
    }

}
