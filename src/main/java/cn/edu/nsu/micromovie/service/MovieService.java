package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.Filter.MovieFilter;
import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service("movieService")
public class MovieService {
    @Autowired
    private MovieMapper movieMapper;

    public List<Movie> selectByFilter(MovieFilter filter){
        ArrayList<Movie> list = (ArrayList<Movie>) movieMapper.selectByFilter(filter);
        Set<Movie> set = new LinkedHashSet<>(list);
        List<Movie> result = new ArrayList<>(set);
        return result;
    }

    public Movie selectById(Integer id){
        return movieMapper.selectByPrimaryKey(id);
    }

    public int del(int id){
        return movieMapper.deleteByPrimaryKey(id);
    }
}
