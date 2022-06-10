package com.segroup.hospitalsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.segroup.hospitalsite.pojo.Guide;
import com.segroup.hospitalsite.pojo.Notice;
import com.segroup.hospitalsite.service.INoticeService;
import com.segroup.hospitalsite.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "page", notes = "可以指定参数的API")
    @GetMapping("/page")
    public JsonResultUtil<Page<Notice>> getPageNotcie(Integer p, Integer pageSize){
        Page<Notice> page = new Page<>(p,pageSize);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        noticeService.page(page);
        return new JsonResultUtil<Page<Notice>>(200,page);
    }

    @ApiOperation(value = "getById", notes = "可以指定参数的API")
    @GetMapping("/getById")
    public JsonResultUtil<Notice> getNoticeById(Long id){
        return new JsonResultUtil<>(1,noticeService.getById(id));
    }

    @ApiOperation(value = "deleteById", notes = "可以指定参数的API")
    @DeleteMapping("/deleteById")
    public JsonResultUtil<Notice> deleteNoticeById(Long id){
        Notice n = noticeService.getById(id);
        Assert.isTrue(noticeService.removeById(n),"删除失败");
        return new JsonResultUtil<Notice>(200);
    }

    @ApiOperation(value = "create", notes = "可以指定参数的API")
    @PostMapping("/create")
    public JsonResultUtil<Notice> createNotice(String title, Integer authorId, String content){
        Notice n = new Notice();
        n.setAuthorId(authorId);
        n.setTitle(title);
        n.setContent(content);
        noticeService.save(n);
        return new JsonResultUtil<Notice>(200);
    }

    @ApiOperation(value = "update", notes = "可以指定参数的API")
    @PostMapping("/update")
    public JsonResultUtil<Notice> updateNotice(Long id, String title, Integer authorId, String content){
        Notice n = noticeService.getById(id);
        n.setAuthorId(authorId);
        n.setTitle(title);
        n.setContent(content);
        noticeService.updateById(n);
        return new JsonResultUtil<Notice>(200);
    }


}
