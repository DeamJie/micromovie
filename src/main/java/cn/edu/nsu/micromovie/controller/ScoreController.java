package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.dao.CollectionMapper;
import cn.edu.nsu.micromovie.dao.ScoreMapper;
import cn.edu.nsu.micromovie.model.Score;
import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.ScoreService;
import cn.edu.nsu.micromovie.util.HandleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private CollectionMapper collectionMapper;

    @PostMapping("/{userId}")
    @ResponseBody
    public HandleResult createScore(@PathVariable("userId") Integer userId, @RequestBody Score score, HttpSession session){
        score.setUserid(userId);
        if (scoreService.insert(score) == 1){
            User temp = (User)session.getAttribute("user");
            Integer scoreLabel = scoreMapper.selectLike(temp.getId());
            Integer collection = collectionMapper.selectLike(temp.getId(),scoreLabel);
            if (scoreLabel!=null){
                temp.getPreference().setScoreLabelId(scoreLabel);
            }
            if (collection!=null){
                temp.getPreference().setConnectionLabelId(collection);
            }
            session.setAttribute("user",temp);
            return HandleResult.success();
        }else {
            return HandleResult.error("保存评分失败！");
        }
    }
}
