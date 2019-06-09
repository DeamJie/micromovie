package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.model.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LabelMapper {
    int deleteByPrimaryKey(Integer labelid);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer labelid);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    List<Label> selectAll();

    Label selectByName(String name);
}