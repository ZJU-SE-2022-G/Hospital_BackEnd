package com.segroup.hospitalsite.IntroductionApp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.IntroductionApp.entity.DoctorIntro;
import com.segroup.hospitalsite.IntroductionApp.service.IDoctorIntroService;
import com.segroup.hospitalsite.utils.CommonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
            value = "用户名", required = true, defaultValue = "test")
    public CommonResult<List<DoctorIntro>> getItem(@PathVariable String id)
    {

        QueryWrapper<DoctorIntro> wrapper = new QueryWrapper<>();
        wrapper.eq("doc_id", id);
        List<DoctorIntro> queryList = iDoctorIntroService.list(wrapper);

        return CommonResult.success(queryList);
    }

}
