package com.segroup.hospitalsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.segroup.hospitalsite.entity.Workday;
import com.segroup.hospitalsite.service.IWorkdayService;
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
 *  前端控制器
 * </p>
 *
 * @author zheng
 * @since 2022-06-12
 */
@Controller
@RequestMapping("/workday")
public class WorkdayController {

    @Autowired
    private IWorkdayService iWorkdayService;

    @ApiOperation("获取所有工作时间信息")
    @RequestMapping(value = "/fetch-all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Workday>> getAllWorkdayInfo()
    {
        QueryWrapper<Workday> wrapper = new QueryWrapper<>();
        List<Workday> queryList = iWorkdayService.list(wrapper);

        return CommonResult.success(queryList);
    }
    @ApiOperation("")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Workday>> getlWorkdayInfoBy(@PathVariable Long id)
    {
        QueryWrapper<Workday> wrapper = new QueryWrapper<>();
        wrapper.eq("wid", id);
        List<Workday> queryList = iWorkdayService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("获取某医生工作时间信息")
    @RequestMapping(value = "/{did}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Workday>> getlWorkdayInfoByName(@PathVariable String did)
    {
        QueryWrapper<Workday> wrapper = new QueryWrapper<>();
        wrapper.eq("did", did);
        List<Workday> queryList = iWorkdayService.list(wrapper);

        return CommonResult.success(queryList);
    }

    @ApiOperation("添加工作信息")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "did", value = "医生编号", required = true),
            @ApiImplicitParam(paramType = "query", name = "workTime", value = "工作时间", required = true),
            @ApiImplicitParam(paramType = "query", name = "totalNum", value = "医生当天号源数量", required = false, defaultValue = "20"),
    })
    public CommonResult<String> insertWorkday(
            @RequestParam String did,
            @RequestParam String workTime,
            @RequestParam Integer totalNum
    ) {
        Workday workday = new Workday();
        workday.setDid(did);
        workday.setWorkTime(workTime);
        workday.setTotalNum(totalNum);
        workday.setOrderedNum(0);
        workday.setState("可被预约的");

        if(iWorkdayService.saveOrUpdate(workday)){
            return CommonResult.success("");
        }
        else
        {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改工作信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "wid", value = "工作编号", required = true),
            @ApiImplicitParam(paramType = "query", name = "did", value = "医生编号", required = false, defaultValue = "-1"),
            @ApiImplicitParam(paramType = "query", name = "workTime", value = "工作时间", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "totalNum", value = "医生当天号源数量", required = false, defaultValue = "-1"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "orderedNum", value = "已预定号源数量", required = false, defaultValue = "-1"),
    })
    public CommonResult<String> updateRecordById(
            @RequestParam Integer wid,
            @RequestParam String did,
            @RequestParam String workTime,
            @RequestParam Integer totalNum,
            @RequestParam Integer orderedNum
    ) {
        QueryWrapper<Workday> wrapper = new QueryWrapper<>();
        wrapper.eq("wid", wid);
        List<Workday> queryList = iWorkdayService.list(wrapper);

        Workday workday = queryList.get(0);
        if(!Objects.equals(did, "-1"))
        {
            workday.setDid(did);
        }
        if(!Objects.equals(workTime, "null"))
        {
            workday.setWorkTime(workTime);
        }
        if(!Objects.equals(totalNum, "-1"))
        {
            workday.setTotalNum(totalNum);
        }
        if(!Objects.equals(orderedNum, "-1"))
        {
            workday.setOrderedNum(orderedNum);
        }
        if(iWorkdayService.saveOrUpdate(workday)){
            return CommonResult.success("");
        }
        else
        {
            return CommonResult.failed();
        }
    }

    @ApiOperation("增加预订号源数量")
    @RequestMapping(value = "/update-order", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "wid", value = "工作编号", required = true)
    })
    public CommonResult<String> updateRecordOrderedNumById(
            @RequestParam Integer wid
    ) {
        QueryWrapper<Workday> wrapper = new QueryWrapper<>();
        wrapper.eq("wid", wid);
        List<Workday> queryList = iWorkdayService.list(wrapper);

        Workday workday = queryList.get(0);
        String state = workday.getState();
        if("已满".equals(state))
        {
            return CommonResult.failed("超过当前最大号源数量");
        }
        else
        {
            int orderedNum = workday.getOrderedNum();
            int totalNum = workday.getTotalNum();
            workday.setOrderedNum(orderedNum+1);

            if(orderedNum + 1== totalNum)
            {
                workday.setState("已满");
            }
            if(iWorkdayService.saveOrUpdate(workday)){
                return CommonResult.success("");
            }
            else
            {
                return CommonResult.failed();
            }
        }
    }

    @ApiOperation("删除指定工作信息")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "wid", value = "工作编号", required = true)
    public CommonResult<String> deletePatientInfoById(@RequestParam Integer wid) {
        QueryWrapper<Workday> wrapper = new QueryWrapper<>();
        wrapper.eq("wid", wid);
        if (iWorkdayService.remove(wrapper)) {
            return CommonResult.success("");
        }
        else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除所有工作信息")
    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<String> deleteAllPatients()
    {
        QueryWrapper<Workday> wrapper = new QueryWrapper<>();
        wrapper.ne("wid", "");
        if(iWorkdayService.remove(wrapper)) {
            return CommonResult.success("");
        }
        else {
            return CommonResult.failed();
        }
    }
}
