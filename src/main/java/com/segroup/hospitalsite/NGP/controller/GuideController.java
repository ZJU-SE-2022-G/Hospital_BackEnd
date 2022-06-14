package com.segroup.hospitalsite.NGP.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.segroup.hospitalsite.NGP.entity.Guide;
import com.segroup.hospitalsite.NGP.service.IGuideService;
import com.segroup.hospitalsite.NGP.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    public static final int SUCCESS = 200;
    public static final int TITLE_NULL_ERROR = 4000;
    public static final int CONTENT_NULL_ERROR = 4001;
    public static final int GUIDE_NULL_ERROR = 4002;

    @ApiOperation(value = "page", notes = "可以指定参数的API")
    @GetMapping("/page")
    public JsonResultUtil<Page<Guide>> getPageGuide(Integer p, Integer pageSize){
        Page<Guide> page = new Page<>(p,pageSize);
        QueryWrapper<Guide> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        guideService.page(page,wrapper);
        return new JsonResultUtil<Page<Guide>>(SUCCESS,page);
    }

    @ApiOperation(value = "getById", notes = "可以指定参数的API")
    @GetMapping("/getById")
    public JsonResultUtil<Guide> getGuideById(Integer id){
        Guide g=guideService.getById(id);
        if(g==null){
            return new JsonResultUtil<Guide>(GUIDE_NULL_ERROR,"由于指南不存在等原因导致获取指南失败");
        }
        return new JsonResultUtil<Guide>(SUCCESS,g);
    }

    @ApiOperation(value = "deleteById", notes = "可以指定参数的API")
    @DeleteMapping("/deleteById")
    public JsonResultUtil<Guide> deleteGuideById(Long id){
        Guide n = guideService.getById(id);
        Assert.isTrue(guideService.removeById(n),"删除失败");
        return new JsonResultUtil<Guide>(SUCCESS);
    }

    @ApiOperation(value = "create", notes = "可以指定参数的API")
    @PostMapping("/create")
    public JsonResultUtil<Guide> createGuide(@RequestBody Map<String, String> rqBody){

        Integer authorId = (Integer) request.getSession().getAttribute("uid");
        String title = rqBody.get("title");
        if(title== null){
            return new JsonResultUtil<Guide>(TITLE_NULL_ERROR,"必须输入标题");
        }
        String content = rqBody.get("content");
        if(content== null){
            return new JsonResultUtil<Guide>(CONTENT_NULL_ERROR,"必须输入内容");
        }
        Guide n = new Guide();
        n.setAuthorId(authorId);
        n.setTitle(title);
        n.setContent(content);
        guideService.save(n);
        return new JsonResultUtil<Guide>(SUCCESS,n);
    }

    @ApiOperation(value = "update", notes = "可以指定参数的API")
    @PostMapping("/update")
    public JsonResultUtil<Guide> updateGuide(@RequestBody Map<String, String> rqBody){
        Integer authorId = (Integer) request.getSession().getAttribute("uid");
        Long id = Long.parseLong(rqBody.get("id"));
        String title = rqBody.get("title");
        if(title== null){
            return new JsonResultUtil<Guide>(TITLE_NULL_ERROR,"必须输入标题");
        }
        String content = rqBody.get("content");
        if(content== null){
            return new JsonResultUtil<Guide>(CONTENT_NULL_ERROR,"必须输入内容");
        }
        Guide n = guideService.getById(id);
        n.setAuthorId(authorId);
        n.setTitle(title);
        n.setContent(content);
        boolean b=guideService.updateById(n);
        if(!b){
            return new JsonResultUtil<Guide>(GUIDE_NULL_ERROR,"由于指南不存在等原因导致更新失败");
        }
        return new JsonResultUtil<Guide>(SUCCESS);
    }
}
