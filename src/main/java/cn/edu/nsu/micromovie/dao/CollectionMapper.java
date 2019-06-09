package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.Filter.CollectionFilter;
import cn.edu.nsu.micromovie.model.Collection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    int deleteByPrimaryKey(Integer collectionid);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer collectionid);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    List<Collection> selectByFilter(CollectionFilter filter);
}