package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.LabelMapper;
import cn.edu.nsu.micromovie.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;

    public List<Label> selectAll(){
        return labelMapper.selectAll();
    }

    public Label selectById(Integer id){
        return labelMapper.selectByPrimaryKey(id);
    }

    public int insert(Label label){
        return labelMapper.insertSelective(label);
    }

    public boolean isExit(String name){
        Label label = labelMapper.selectByName(name);
        if (label == null){
            return false;
        }else {
            return true;
        }
    }
}
