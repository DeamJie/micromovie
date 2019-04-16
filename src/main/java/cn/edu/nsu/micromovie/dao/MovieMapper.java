package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.model.Movie;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface MovieMapper {
    String SELECT_KEY = "id,name,translationname,time,country,score,intro,labelid,img,download";
    String INSERT_KEY ="name,translationname,time,country,score,intro,labelid,img,download";

    @Select({"select " ,SELECT_KEY,"from movie where labelid = #{labelid}"})
    List<Movie> selectByLabel(int labelid);

    @Select({"select " ,SELECT_KEY,"from movie where time = #{time}"})
    List<Movie> selectByTime(int time);

    @Select({"select " ,SELECT_KEY,"from movie where name = #{name}"})
    List<Movie> selectByName(String  name);

    @Select({"select " ,SELECT_KEY,"from movie where id = #{id}"})
    Movie selectById(int id);

    @Insert({"insert into movie" ,"(" ,INSERT_KEY,") values (#{name},#{translationname},#{time},#{country},#{score},#{intro}," +
            "#{labelid},#{img},#{download})"})
    void insertMovie(Movie movie);

    @Select({"select " ,SELECT_KEY,"from movie where time > #{time}"})
    List<Movie> selectBiggerTime(int time);

    @Select({"select " ,SELECT_KEY,"from movie where time < #{time}"})
    List<Movie> selectSmallTime(int time);
}