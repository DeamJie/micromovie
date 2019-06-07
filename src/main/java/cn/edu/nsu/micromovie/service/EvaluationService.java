package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.EvaluationMapper;
import cn.edu.nsu.micromovie.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    public int insertEvaluation(Evaluation evaluation){
        return evaluationMapper.insertSelective(evaluation);
    }
}
