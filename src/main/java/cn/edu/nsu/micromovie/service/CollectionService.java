package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.CollectionMapper;
import cn.edu.nsu.micromovie.model.Collection;
import cn.edu.nsu.micromovie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;
    public List<Collection> getCollectionMovie(int uId){
        ArrayList<Collection> collections=(ArrayList)collectionMapper.selectByUserId(uId);
        return collections;
    }

    public Map<String,Object> addToCollection(int userId, int movieId){
        Map<String,Object> map = new HashMap<>();
        ArrayList<Collection> collections=(ArrayList)collectionMapper.selectByUserId(userId);
        for(Collection collection:collections){
            if(collection.getMovieid()==movieId){
                map.put("collectMsg","已经收藏了");
                return map;
            }
        }
        map.put("collectMsg","收藏成功");
        return map;
    }
}
