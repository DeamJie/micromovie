package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.Score;
import cn.edu.nsu.micromovie.service.ScoreService;
import cn.edu.nsu.micromovie.util.HandleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @PostMapping("/{userId}")
    @ResponseBody
    public HandleResult createScore(@PathVariable("userId") Integer userId, @RequestBody Score score){
        score.setUserid(userId);
        System.out.println(score.getMoveid());
        if (scoreService.insert(score) == 1){
            return HandleResult.success();
        }else {
            return HandleResult.error("保存评分失败！");
        }
    }
}
