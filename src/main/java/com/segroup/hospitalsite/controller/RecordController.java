package com.segroup.hospitalsite.controller;

import com.segroup.hospitalsite.entity.Record;
import com.segroup.hospitalsite.entity.User;
import com.segroup.hospitalsite.service.IRecordService;
import com.segroup.hospitalsite.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zheng
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    private IRecordService iRecordService;

    @ApiOperation("返回所有信息")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Record> listAll()
    {
        List<Record> records = iRecordService.list();
        return records;
    }
}
