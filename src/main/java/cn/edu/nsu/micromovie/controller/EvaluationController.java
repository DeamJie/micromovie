package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.Evaluation;
import cn.edu.nsu.micromovie.service.EvaluationService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
}
