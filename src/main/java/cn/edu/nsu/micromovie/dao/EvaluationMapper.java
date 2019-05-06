package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.model.Evaluation;

public interface EvaluationMapper {
    int deleteByPrimaryKey(Integer evaluationid);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer evaluationid);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);
}