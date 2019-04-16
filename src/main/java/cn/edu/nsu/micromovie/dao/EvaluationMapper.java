package cn.edu.nsu.micromovie.dao;

import cn.edu.nsu.micromovie.model.Evaluation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评价的表
 */
@Mapper
public interface EvaluationMapper {
    String SELECT_KEY = "evaluationid,uid,movieid,evaluation";
    String INSERT_KEY = "uid,movieid,evaluation";

    @Select({"select",SELECT_KEY ,"from evaluation where movieid = #{movieid}"})
    List<Evaluation> selectByMovieId(int movieId);
    @Select({"select",SELECT_KEY,"from evaluation where uid=#{userID}"})
    List<Evaluation> selectByUserId(int userId);
    @Insert({"insert into evaluation" ,"(" ,INSERT_KEY,") values (#{uid},#{movieid},#{evaluation})"})
    void insertEvaluation(Evaluation evaluation);
    @Delete("delete from evaluation where evaluationid=#{evaluationid}")
    void deleteByid(int evaluationid);
}