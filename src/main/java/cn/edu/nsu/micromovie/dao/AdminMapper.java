package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.model.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Insert("insert into admin (name,passworld,salt) values (#{name},#{passworld},#{salt})")
    void insertAdmin(Admin admin);
    @Select("select * from admin where name = #{name}")
    Admin selectByName(String name);
}