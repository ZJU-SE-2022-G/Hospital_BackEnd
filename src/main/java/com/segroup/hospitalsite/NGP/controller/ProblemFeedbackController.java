package com.segroup.hospitalsite.NGP.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.segroup.hospitalsite.NGP.entity.ProblemFeedback;
import com.segroup.hospitalsite.NGP.service.IProblemFeedbackService;
import com.segroup.hospitalsite.NGP.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
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
@RequestMapping("/problem-feedback")
@Api(tags = "problemFeedback")
public class ProblemFeedbackController {
    @Autowired
    private IProblemFeedbackService problemFeedbackService;

    @Autowired
    private HttpServletRequest request;
    public static final int SUCCESS = 200;
    public static final int PROBLEM_NULL_ERROR = 4000;
    public static final int FEEDBACK_NULL_ERROR = 4001;
    public static final int ANSWER_NULL_ERROR = 4002;
    public static final int TITLE_NULL_ERROR=4003;

    @ApiOperation(value = "page", notes = "可以指定参数的API")
    @PostMapping("/page")
    public JsonResultUtil<Page<ProblemFeedback>> getAllProblem(Integer p, Integer pageSize, String query){
        Page<ProblemFeedback> page = new Page<>(p,pageSize);
        QueryWrapper<ProblemFeedback> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("res_time");
        if(query!=null){
            wrapper.nested(i -> i.like("problem",query).or().like("answer",query).or().like("title",query));
        }
        problemFeedbackService.page(page,wrapper);
        return new JsonResultUtil<Page<ProblemFeedback>>(SUCCESS,page);
    }

    @ApiOperation(value = "getById", notes = "可以指定参数的API")
    @GetMapping("/getById")
    public JsonResultUtil<ProblemFeedback> getProblemById(Long id){
        return new JsonResultUtil<>(SUCCESS,problemFeedbackService.getById(id));
    }

    @ApiOperation(value = "create", notes = "可以指定参数的API")
    @PostMapping("/create")
    public  JsonResultUtil<ProblemFeedback> createProblem(@RequestBody Map<String, String> rqBody) {
        Integer askerId = (Integer) request.getSession().getAttribute("uid");
        String title = rqBody.get("title");
        if(title==null){
            return new JsonResultUtil<ProblemFeedback>(TITLE_NULL_ERROR, "必须输入问题题目");
        }
        String problem = rqBody.get("problem");
        if(problem==null){
            return new JsonResultUtil<ProblemFeedback>(PROBLEM_NULL_ERROR, "必须输入问题");
        }
        String problemType = rqBody.get("problemType");
        ProblemFeedback p = new ProblemFeedback();
        p.setProblem(problem);
        p.setTitle(title);
        p.setIsAnswered(0);
        p.setProblemType(problemType);
        p.setAskerId(askerId);
        problemFeedbackService.save(p);
        return new JsonResultUtil<ProblemFeedback>(SUCCESS, p);
    }

    @ApiOperation(value = "answer", notes = "可以指定参数的API")
    @PostMapping("/answer")
    public JsonResultUtil<ProblemFeedback> answerProblem(@RequestBody Map<String, String> rqBody){
        Integer respondentID = (Integer) request.getSession().getAttribute("uid");
        Long id = Long.parseLong(rqBody.get("id"));
        System.out.println(id);
        String answer = rqBody.get("answer");
        System.out.println(answer);
        if(answer==null){
            return new JsonResultUtil<ProblemFeedback>(ANSWER_NULL_ERROR, "必须输入回答的答案");
        }
        ProblemFeedback p = problemFeedbackService.getById(id);
        p.setAnswer(answer);
        p.setIsAnswered(1);
        p.setRespondentId(respondentID);
        boolean b = problemFeedbackService.updateById(p);
        if(!b){
            return new JsonResultUtil<ProblemFeedback>(FEEDBACK_NULL_ERROR,"该问题以及不存在等原因导致回答失败");
        }
        return new JsonResultUtil<ProblemFeedback>(SUCCESS,p);
    }

    @ApiOperation(value = "deleteById", notes = "可以指定参数的API")
    @DeleteMapping("/deleteById")
    public JsonResultUtil<ProblemFeedback> deleteProblemById(Long id){
        ProblemFeedback p = problemFeedbackService.getById(id);
        Assert.isTrue(problemFeedbackService.removeById(p),"删除失败");
        return new JsonResultUtil<>(SUCCESS,p);
    }
}
