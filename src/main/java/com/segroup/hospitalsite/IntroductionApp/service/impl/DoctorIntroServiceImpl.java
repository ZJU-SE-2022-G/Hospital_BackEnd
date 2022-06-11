package com.segroup.hospitalsite.IntroductionApp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.IntroductionApp.entity.DoctorIntro;
import com.segroup.hospitalsite.mapper.DoctorIntroMapper;
import com.segroup.hospitalsite.IntroductionApp.service.IDoctorIntroService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 医生信息介绍表 服务实现类
 * </p>
 *
 * @author yuguoyao
 * @since 2022-06-05
 */
@Service
public class DoctorIntroServiceImpl extends ServiceImpl<DoctorIntroMapper, DoctorIntro> implements IDoctorIntroService {

}
