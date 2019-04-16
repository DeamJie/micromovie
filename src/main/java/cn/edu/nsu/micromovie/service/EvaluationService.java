package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.EvaluationMapper;
import cn.edu.nsu.micromovie.model.Evaluation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;
    //增加评论
    public Map<String,Object> addEvaluation(int userId,int movieID,String evaluationString){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(evaluationString)){
            map.put("evalutionMsg","评论不能为空");
            return map;
        }
        Evaluation evaluation = new Evaluation();
        evaluation.setUserid(userId);
        evaluation.setMovieid(movieID);
        evaluation.setEvaluation(evaluationString);
        evaluationMapper.insertEvaluation(evaluation);
        map.put("evalutionMsg","评论成功");
        return map;
    }
    //查看电影的评论
    public List<Evaluation> getEvaluationOfMovie(int movieId){
        List<Evaluation> evaluations = new ArrayList<>();
        evaluationMapper.selectByMovieId(movieId);
        return evaluations;
    }
    //查看用户的评论
    public List<Evaluation> getEvaluationOfUser(int userId){
        List<Evaluation> evaluations = new ArrayList<>();
        evaluationMapper.selectByMovieId(userId);
        return evaluations;
    }
    //删除评论
    public void deleteEvaluation(int evaluationId){
        evaluationMapper.deleteByid(evaluationId);
    }
}
