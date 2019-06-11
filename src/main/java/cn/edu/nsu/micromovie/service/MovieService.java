package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.Filter.MovieFilter;
import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.model.Movie;
import cn.edu.nsu.micromovie.util.recommend.Preference;
import groovy.util.IFileNameFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Movie> selectLike(Preference preference){
        if (preference.getClickLabelId()==0&&preference.getScoreLabelId()==0&&preference.getConnectionLabelId()==0){
            return null;
        }
        if (preference.getScoreLabelId()==0&&preference.getConnectionLabelId()>0){
            double scale = (preference.getScoreLabelScale()+preference.getConnectionLabelScale());
            if (preference.getClickLabelId()>0){
                if (preference.getConnectionLabelId()==preference.getClickLabelId()){
                    return movieMapper.selectByLabelId(preference.getConnectionLabelId(),40);
                }else {
                    List<Movie> conList = movieMapper.selectByLabelId(preference.getConnectionLabelId(),(int)(40*scale));
                    List<Movie> cliList = movieMapper.selectByLabelId(preference.getClickLabelId(),(int)(40*preference.getClickLabelScale()));
                    if(conList.addAll(cliList)){
                        Set set = new HashSet(conList);
                        ArrayList<Movie> result = new ArrayList<>(set);
                        return result;
                    }

                }
            }else return movieMapper.selectByLabelId(preference.getConnectionLabelId(),40);
        }
        if (preference.getScoreLabelId()>0&&preference.getConnectionLabelId()==0){
            double scale=(preference.getScoreLabelScale()+preference.getConnectionLabelScale());
            if (preference.getClickLabelId()>0){
                if (preference.getScoreLabelId()==preference.getClickLabelId()){
                    return movieMapper.selectByLabelId(preference.getScoreLabelId(),40);
                }else {
                    List<Movie> sList = movieMapper.selectByLabelId(preference.getScoreLabelId(),(int)(40*scale));
                    List<Movie> cliList = movieMapper.selectByLabelId(preference.getClickLabelId(),(int)(40*preference.getClickLabelScale()));
                    if(sList.addAll(cliList)){
                        Set set = new HashSet(sList);
                        ArrayList<Movie> result = new ArrayList<>(set);
                        return result;
                    }
                }
            }else return movieMapper.selectByLabelId(preference.getScoreLabelId(),40);
        }

        if (preference.getScoreLabelId()==0&&preference.getConnectionLabelScale()==0&&preference.getClickLabelScale()>0){
            return movieMapper.selectByLabelId(preference.getClickLabelId(),40);
        }

        if (preference.getScoreLabelId()>0&&preference.getConnectionLabelId()>0){
            if (preference.getClickLabelId()>0){
                if (preference.getScoreLabelId()==preference.getClickLabelId()){
                    double scale=(preference.getScoreLabelScale()+preference.getClickLabelScale());
                    List<Movie> sList = movieMapper.selectByLabelId(preference.getScoreLabelId(),(int)(40*scale));
                    List<Movie> cList = movieMapper.selectByLabelId(preference.getConnectionLabelId(),(int)(40*preference.getConnectionLabelScale()));
                    if (sList.addAll(cList)){
                        Set set = new HashSet(sList);
                        ArrayList<Movie> result = new ArrayList<>(set);
                        return result;
                    }
                }
                else if (preference.getConnectionLabelId()==preference.getClickLabelId()){
                    double scale=(preference.getConnectionLabelScale()+preference.getClickLabelScale());
                    List<Movie> sList = movieMapper.selectByLabelId(preference.getScoreLabelId(),(int)(40*preference.getScoreLabelScale()));
                    List<Movie> cList = movieMapper.selectByLabelId(preference.getConnectionLabelId(),(int)(40*scale));
                    if (sList.addAll(cList)){
                        Set set = new HashSet(sList);
                        ArrayList<Movie> result = new ArrayList<>(set);
                        return result;
                    }
                }
                else {
                    List<Movie> sList = movieMapper.selectByLabelId(preference.getScoreLabelId(),(int)(40*preference.getScoreLabelScale()));
                    List<Movie> cList = movieMapper.selectByLabelId(preference.getConnectionLabelId(),(int)(40*preference.getConnectionLabelScale()));
                    List<Movie> clickList = movieMapper.selectByLabelId(preference.getClickLabelId(),(int)(40*preference.getClickLabelScale()));
                    sList.addAll(cList);
                    sList.addAll(clickList);
                    Set set = new HashSet(sList);
                    ArrayList<Movie> result = new ArrayList<>(set);
                    return result;
                }
            }else {
                List<Movie> sList = movieMapper.selectByLabelId(preference.getScoreLabelId(),(int)(40*preference.getScoreLabelScale()));
                List<Movie> cList = movieMapper.selectByLabelId(preference.getConnectionLabelId(),(int)(40*preference.getConnectionLabelScale()));
                sList.addAll(cList);
                Set set = new HashSet(sList);
                ArrayList<Movie> result = new ArrayList<>(set);
                return result;
            }
        }
        return null;
    }
}
