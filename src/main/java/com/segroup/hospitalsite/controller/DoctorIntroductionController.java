package com.segroup.hospitalsite.controller;

import com.segroup.hospitalsite.entity.DoctorIntroduction;
import com.segroup.hospitalsite.service.IDoctorIntroductionService;
import com.segroup.hospitalsite.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class DoctorIntroductionController {

    private IDoctorIntroductionService iDoctorIntroductionService;

    @ApiOperation("获取指定名医介绍")
    @RequestMapping(value = "{/id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<DoctorIntroduction> getItem(@PathVariable Long id)
    {
        DoctorIntroduction doc_intro = iDoctorIntroductionService.getById(id);
        return CommonResult.success(doc_intro);
    }

}
