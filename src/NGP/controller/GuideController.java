package com.segroup.hospitalsite.NGP.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.segroup.hospitalsite.NGP.entity.Guide;
import com.segroup.hospitalsite.NGP.service.IGuideService;
import com.segroup.hospitalsite.NGP.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Liu Yibin
 * @since 2022-5-26
 */
@RestController
@RequestMapping("/guide")
@Api(tags = "guide")
public class GuideController {
    @Autowired
    private IGuideService guideService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "page", notes = "可以指定参数的API")
    @GetMapping("/page")
    public JsonResultUtil<Page<Guide>> getPageGuide(Integer p, Integer pageSize){
        Page<Guide> page = new Page<>(p,pageSize);
        QueryWrapper<Guide> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        guideService.page(page);
        return new JsonResultUtil<Page<Guide>>(200,page);
    }

    @ApiOperation(value = "getById", notes = "可以指定参数的API")
    @GetMapping("/getById")
    public JsonResultUtil<Guide> getGuideById(Integer id){
        return new JsonResultUtil<>(200,guideService.getById(id));
    }

    @ApiOperation(value = "deleteById", notes = "可以指定参数的API")
    @DeleteMapping("/deleteById")
    public JsonResultUtil<Guide> deleteGuideById(Long id){
        Guide n = guideService.getById(id);
        Assert.isTrue(guideService.removeById(n),"删除失败");
        return new JsonResultUtil<Guide>(200);
    }

    @ApiOperation(value = "create", notes = "可以指定参数的API")
    @PostMapping("/create")
    public JsonResultUtil<Guide> createGuide(String title, String content){

        Integer authorId = (Integer) request.getSession().getAttribute("uid");

        Guide n = new Guide();
        n.setAuthorId(authorId);
        n.setTitle(title);
        n.setContent(content);
        guideService.save(n);
        return new JsonResultUtil<Guide>(200,n);
    }

    @ApiOperation(value = "update", notes = "可以指定参数的API")
    @PostMapping("/update")
    public JsonResultUtil<Guide> updateGuide(Integer id, String title, String content){
        Integer authorId = (Integer) request.getSession().getAttribute("uid");
        Guide n = guideService.getById(id);
        n.setAuthorId(authorId);
        n.setTitle(title);
        n.setContent(content);
        guideService.updateById(n);
        return new JsonResultUtil<Guide>(200);
    }
}
