package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.ScoreMapper;
import cn.edu.nsu.micromovie.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    public Integer insert(Score score){
        if (isExit(score)){
            return scoreMapper.updateByPrimaryKeySelective(score);
        }else {
            return scoreMapper.insertSelective(score);
        }
    }

    public boolean isExit(Score score){
        Score temp = scoreMapper.select(score);
        if (temp == null){
            return false;
        }else {
            score.setId(temp.getId());
            return true;
        }
    }
}
