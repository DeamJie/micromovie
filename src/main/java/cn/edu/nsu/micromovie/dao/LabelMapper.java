package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.model.Label;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LabelMapper {
    String INSET_FIELDS = "name,survive";

    @Select("select * from label")
     List<Label> selectAllLabel();

    @Select("select * from label where labelid = #{id}")
     Label selectLableByID(int id);

    @Insert({"insert into label" ,"(",INSET_FIELDS,") values (#{name},#{survive})" })
     void insertLabel(Label label);

    @Update("update form label set survive = 0 where labelid = #{id}")
    void updateSurvive(int id);

    @Select("select * from label where name = #{name}")
    Label selectLableByName(String name);
}