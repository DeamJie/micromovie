package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.Filter.MovieFilter;
import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieService")
public class MovieService {
    @Autowired
    private MovieMapper movieMapper;

    public List<Movie> selectByFilter(MovieFilter filter){
        return movieMapper.selectByFilter(filter);
    }

    public Movie selectById(Integer id){
        return movieMapper.selectByPrimaryKey(id);
    }
}
