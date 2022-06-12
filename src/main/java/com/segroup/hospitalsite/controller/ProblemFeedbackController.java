package com.segroup.hospitalsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.segroup.hospitalsite.pojo.Notice;
import com.segroup.hospitalsite.pojo.ProblemFeedback;
import com.segroup.hospitalsite.service.IProblemFeedbackService;
import com.segroup.hospitalsite.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
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
@RequestMapping("/problem-feedback")
@Api(tags = "problemFeedback")
public class ProblemFeedbackController {
    @Autowired
    private IProblemFeedbackService problemFeedbackService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "page", notes = "可以指定参数的API")
    @PostMapping("/page")
    public JsonResultUtil<Page<ProblemFeedback>> getAllProblem(Integer p, Integer pageSize){
        Page<ProblemFeedback> page = new Page<>(p,pageSize);
        QueryWrapper<ProblemFeedback> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        problemFeedbackService.page(page);
        return new JsonResultUtil<Page<ProblemFeedback>>(200,page);
    }

    @ApiOperation(value = "getById", notes = "可以指定参数的API")
    @GetMapping("/getById")
    public JsonResultUtil<ProblemFeedback> getProblemById(Long id){
        return new JsonResultUtil<>(200,problemFeedbackService.getById(id));
    }

    @ApiOperation(value = "create2", notes = "可以指定参数的API")
    @PostMapping("/create2")
    public  JsonResultUtil<ProblemFeedback> createProblem(String problem, String problemType){
        Integer askerId = (Integer) request.getSession().getAttribute("uid");
        ProblemFeedback p = new ProblemFeedback();
        p.setProblem(problem);
        p.setProblemType(problemType);
        p.setAskerId(askerId);
        problemFeedbackService.save(p);
        return new JsonResultUtil<>(200,p);
    }

    @ApiOperation(value = "create1", notes = "可以指定参数的API")
    @PostMapping("/create1")
    public JsonResultUtil<ProblemFeedback> createProblem(String problem){
        Integer askerId = (Integer) request.getSession().getAttribute("uid");
        ProblemFeedback p = new ProblemFeedback();
        p.setProblem(problem);
        p.setAskerId(askerId);
        problemFeedbackService.save(p);
        return new JsonResultUtil<>(200,p);
    }

    @ApiOperation(value = "answer", notes = "可以指定参数的API")
    @PostMapping("/answer")
    public JsonResultUtil<ProblemFeedback> answerProblem(Long id, String answer){
        Integer respondentID = (Integer) request.getSession().getAttribute("uid");
        ProblemFeedback p = problemFeedbackService.getById(id);
        p.setAnswer(answer);
        p.setRespondentId(respondentID);
        problemFeedbackService.updateById(p);
        return new JsonResultUtil<>(200,p);
    }

    @ApiOperation(value = "deleteById", notes = "可以指定参数的API")
    @DeleteMapping("/deleteById")
    public JsonResultUtil<ProblemFeedback> deleteProblemById(Long id){
        ProblemFeedback p = problemFeedbackService.getById(id);
        Assert.isTrue(problemFeedbackService.removeById(p),"删除失败");
        return new JsonResultUtil<>(200,p);
    }
}
