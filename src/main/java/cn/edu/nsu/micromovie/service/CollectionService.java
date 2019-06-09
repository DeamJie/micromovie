package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.Filter.CollectionFilter;
import cn.edu.nsu.micromovie.dao.CollectionMapper;
import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.model.Collection;
import cn.edu.nsu.micromovie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private MovieMapper movieMapper;

    public List<Movie> selectByfilter(CollectionFilter filter){
        List<Collection> list =  collectionMapper.selectByFilter(filter);
        List<Movie> resultList = new ArrayList<>();
        for (Collection c:list){
            resultList.add(movieMapper.selectByPrimaryKey(c.getMovieid()));
        }
        return resultList;
    }
}
