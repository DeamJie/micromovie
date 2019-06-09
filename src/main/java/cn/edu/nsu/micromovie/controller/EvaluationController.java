package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.Filter.EvaluationFilter;
import cn.edu.nsu.micromovie.dto.EvaluationDto;
import cn.edu.nsu.micromovie.model.Evaluation;
import cn.edu.nsu.micromovie.service.EvaluationService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @PostMapping (value = "/add")
    @ResponseBody
    public String createEvaluation(@RequestBody String s){
        Date date = new Date(System.currentTimeMillis());
        JSONObject object = JSON.parseObject(s);
        Evaluation evaluation = object.toJavaObject(Evaluation.class);
        evaluation.setDate(date);
        int result = evaluationService.insertEvaluation(evaluation);
        if(result == 1){
            return "评论成功";
        }else {
            return "评论失败";
        }
    }

    @GetMapping("/list/{userId}/{pageNum}")
    public String getEvaluationList(Model model, @PathVariable("userId") Integer userId , @PathVariable("pageNum") Integer pageNum){
        EvaluationFilter filter = new EvaluationFilter();
        if (pageNum-1!=0){
            pageNum = 1;
        }
        filter.setUseId(userId);
        filter.setRows(pageNum-1);
        filter.setOffset(4);
        List<EvaluationDto> list = evaluationService.selectByUserID(filter);
        model.addAttribute("list",list);
        return "/comments";
    }
}
