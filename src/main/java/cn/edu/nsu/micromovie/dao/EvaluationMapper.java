package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.Filter.EvaluationFilter;
import cn.edu.nsu.micromovie.model.Evaluation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvaluationMapper {
    int deleteByPrimaryKey(Integer evaluationid);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer evaluationid);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);

    List<Evaluation> selectByUserId(EvaluationFilter filter);

    List<Evaluation> selectByMovieId(EvaluationFilter filter);

    List<Evaluation> selectAll(EvaluationFilter filter);
}