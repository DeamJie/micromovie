package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.Filter.UserFilter;
import cn.edu.nsu.micromovie.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByName(String name);

    User selectByMail(String mail);

    List<User> selectAll(UserFilter filter);

}