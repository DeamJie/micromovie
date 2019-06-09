package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.Filter.EvaluationFilter;
import cn.edu.nsu.micromovie.dao.EvaluationMapper;
import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.dao.UserMapper;
import cn.edu.nsu.micromovie.dto.EvaluationDto;
import cn.edu.nsu.micromovie.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MovieMapper movieMapper;

    public int insertEvaluation(Evaluation evaluation){
        return evaluationMapper.insertSelective(evaluation);
    }

    public List<EvaluationDto> selectByUserID(EvaluationFilter filter){
        List<Evaluation> list = evaluationMapper.selectByUserId(filter);
        List<EvaluationDto> result = new ArrayList<>();
        for (Evaluation e:list){
            result.add(change(e));
        }
        return result;
    }

    public EvaluationDto change(Evaluation evaluation){
        EvaluationDto evaluationDto = new EvaluationDto();
        evaluationDto.setName(userMapper.selectByPrimaryKey(evaluation.getUid()).getName());
        evaluationDto.setMovieName(movieMapper.selectByPrimaryKey(evaluation.getMovieid()).getName());
        evaluationDto.setEvaluation(evaluation.getEvaluation());
        evaluationDto.setDate(evaluation.getDate());
        return evaluationDto;
    }
}
