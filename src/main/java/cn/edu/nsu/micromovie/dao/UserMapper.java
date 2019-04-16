package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    String SELECT_KEY ="id,name,mail,phonenumber,passworld,salt,headportrait,introduction,freeze";
    String INSET_KEY="name,mail,phonenumber,passworld,salt,headportrait,introduction,freeze";
    @Select({"select ",SELECT_KEY,"from user where mail=#{mail}"})
    User selectByMail(String mail);
    @Insert({"insert into user","(",INSET_KEY,") values (#{name},#{mail},#{phonenumber},#{passworld},#{salt},#{headportrait},#{introduction},#{freeze})"})
    void insertUser(User user);
    @Update("update user set freeze =#{status} where id=#{id}")
    void updateFreeze(@Param("id") int id,@Param("status") int status);
    @Select("select * from user")
    List<User> selectAllUser();
    @Update("update user set name=#{name},mail=#{mail},phonenumber=#{phonenumber},passworld=#{passworld}" +
            ",salt=#{salt},headportrait=#{headportrait},introduction=#{introduction},freeze=#{freeze} where id=#{id}")
    void updateByID(User user);
    @Select({"select ",SELECT_KEY,"from user where name=#{name}"})
    User selectByName(String name);
    @Select({"select ",SELECT_KEY,"from user where id=#{id}"})
    User selectById(int id );
}