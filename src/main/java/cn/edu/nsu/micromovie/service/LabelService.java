package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.LabelMapper;
import cn.edu.nsu.micromovie.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;

    public Map<String,Object> addLabel(String name){
        Map<String,Object> map = new HashMap<>();
        if (labelMapper.selectLableByName(name)!=null){
            map.put("labelMsg","标签已经存在");
            return map;
        }
        Label label = new Label();
        label.setName(name);
        label.setSurvive(1);
        labelMapper.insertLabel(label);
        map.put("lableMsg","添加成功");
        return map;
    }

    public Label getLabelById(int id){
        Label label = labelMapper.selectLableByID(id);
        return label;
    }

    public void deleteLabel(int id){
        labelMapper.updateSurvive(id);
    }

    public List<Label> getLabel(){
        List<Label> labels = new ArrayList<>();
        labels = labelMapper.selectAllLabel();
        return labels;
    }

    public Label getLabelByName(String name){
        return labelMapper.selectLableByName(name);
    }
}
