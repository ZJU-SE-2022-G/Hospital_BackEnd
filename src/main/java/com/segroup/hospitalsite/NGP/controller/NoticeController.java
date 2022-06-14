package com.segroup.hospitalsite.NGP.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.segroup.hospitalsite.NGP.entity.Guide;
import com.segroup.hospitalsite.NGP.entity.Notice;
import com.segroup.hospitalsite.NGP.service.INoticeService;
import com.segroup.hospitalsite.NGP.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/notice")
@Api(tags = "notice")
public class NoticeController {
    @Autowired
    private INoticeService noticeService;

    @Autowired
    private HttpServletRequest request;

    public static final int SUCCESS = 200;
    public static final int TITLE_NULL_ERROR = 4000;
    public static final int CONTENT_NULL_ERROR = 4001;
    public static final int NOTICE_NULL_ERROR = 4002;

    @ApiOperation(value = "page", notes = "可以指定参数的API")
    @GetMapping("/page")
    public JsonResultUtil<Page<Notice>> getPageNotcie(Integer p, Integer pageSize){
        Page<Notice> page = new Page<>(p,pageSize);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        noticeService.page(page, wrapper);
        return new JsonResultUtil<Page<Notice>>(SUCCESS,page);
    }

    @ApiOperation(value = "getById", notes = "可以指定参数的API")
    @GetMapping("/getById")
    public JsonResultUtil<Notice> getNoticeById(Long id){
        Notice n=noticeService.getById(id);
        if(n==null){
            return new JsonResultUtil<Notice>(NOTICE_NULL_ERROR,"由于通知或者公告不存在等原因导致获取失败");
        }
        return new JsonResultUtil<Notice>(SUCCESS,n);
    }

    @ApiOperation(value = "deleteById", notes = "可以指定参数的API")
    @DeleteMapping("/deleteById")
    public JsonResultUtil<Notice> deleteNoticeById(Long id){
        Notice n = noticeService.getById(id);
        Assert.isTrue(noticeService.removeById(n),"删除失败");
        return new JsonResultUtil<Notice>(SUCCESS,n);
    }

    @ApiOperation(value = "create", notes = "可以指定参数的API")
    @PostMapping("/create")
    public JsonResultUtil<Notice> createNotice(@RequestBody Map<String, String> rqBody){
        Integer authorId = (Integer) request.getSession().getAttribute("uid");
        String title = rqBody.get("title");
        if(title== null){
            return new JsonResultUtil<Notice>(TITLE_NULL_ERROR,"必须输入标题");
        }
        String content = rqBody.get("content");
        if(content== null){
            return new JsonResultUtil<Notice>(CONTENT_NULL_ERROR,"必须输入内容");
        }
        Notice n = new Notice();
        n.setAuthorId(authorId);
        n.setTitle(title);
        n.setContent(content);
        noticeService.save(n);
        return new JsonResultUtil<Notice>(SUCCESS,n);
    }

    @ApiOperation(value = "update", notes = "可以指定参数的API")
    @PostMapping("/update")
    public JsonResultUtil<Notice> updateNotice(@RequestBody Map<String, String> rqBody){
        Integer authorId = (Integer) request.getSession().getAttribute("uid");
        Long id = Long.parseLong(rqBody.get("id"));
        String title = rqBody.get("title");
        if(title== null){
            return new JsonResultUtil<Notice>(TITLE_NULL_ERROR,"必须输入标题");
        }
        String content = rqBody.get("content");
        if(content== null){
            return new JsonResultUtil<Notice>(CONTENT_NULL_ERROR,"必须输入内容");
        }
        Notice n = noticeService.getById(id);
        n.setAuthorId(authorId);
        n.setTitle(title);
        n.setContent(content);
        boolean b=noticeService.updateById(n);
        if(!b){
            return new JsonResultUtil<Notice>(NOTICE_NULL_ERROR,"由于通知或者公告不存在等原因导致更新失败");
        }
        return new JsonResultUtil<Notice>(SUCCESS,n);
    }


}
