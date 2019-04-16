package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.LabelMapper;
import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    private MovieMapper movieMapper;

    public Movie getMovie(int id){
        Movie movie = movieMapper.selectById(id);
        return movie;
    }

    public List<Movie> getMovieByTime(int time){
        List<Movie> movies = new ArrayList<>();
        movies = movieMapper.selectByTime(time);
        return movies;
    }

    public List<Movie> getMovieByLabel(int id){
        List<Movie> movies = new ArrayList<>();
        movies = movieMapper.selectByLabel(id);
        return movies;
    }

    public List<Movie> getMovieByName(String name){
        List<Movie> movies = new ArrayList<>();
        movies = movieMapper.selectByName(name);
        //怎么去重？？？
        return movies;
    }

    public Map<String,Object> addMovie(){
        Map<String,Object> map = new HashMap<>();
        return map;
    }

    public List<Movie> getOldMovie(int time){
        return movieMapper.selectSmallTime(time);
    }

    public List<Movie> getNewMovie(int time){
        return movieMapper.selectBiggerTime(time);
    }
}
