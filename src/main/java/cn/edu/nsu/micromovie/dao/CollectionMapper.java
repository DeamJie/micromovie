package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.model.Collection;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionMapper {
    String INSERT_KEY = "uid,movieid";
    String SELECT_KEY="collectionid,uid,movieid";
    @Insert({"insert into collection","(",INSERT_KEY,") values (#{uid},#{movieid})"})
    void insertCollection(Collection collection);
    @Select({"select ",INSERT_KEY,"from collection where uid = #{uId}"})
    List<Collection> selectByUserId(int uId);
}