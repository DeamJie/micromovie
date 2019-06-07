package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.Filter.MovieFilter;
import cn.edu.nsu.micromovie.model.Label;
import cn.edu.nsu.micromovie.model.Movie;
import cn.edu.nsu.micromovie.service.LabelService;
import cn.edu.nsu.micromovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private LabelService labelService;

    @GetMapping("/list/{time}/{pageNum}")
    public String movieList(@PathVariable("time") String time, Model model,@PathVariable("pageNum") Integer pageNum){
        MovieFilter filter = new MovieFilter();
        filter.setTime(Integer.valueOf(time));
        Integer totalPageNum =movieService.selectByFilter(filter).size()/12;
        if(pageNum-1<0){
            pageNum=1;
        }
        if (pageNum>totalPageNum){
            pageNum=totalPageNum;
        }
        filter.setOffset(12);
        filter.setRows((pageNum-1)*12);
        ArrayList<Movie> movieList = (ArrayList<Movie>)movieService.selectByFilter(filter);
        ArrayList<Label> lableList = (ArrayList<Label>)labelService.selectAll();
        model.addAttribute("movieList" , movieList);
        model.addAttribute("lableList" , lableList);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("totalPage",totalPageNum);
        model.addAttribute("time",time);
        return "index";
    }

    @GetMapping("/{movieid}")
    public String getMovie(@PathVariable("movieid") Integer id , Model model){
        model.addAttribute("movie",movieService.selectById(id));
        labelService.selectById(movieService.selectById(id).getLabelid());
        model.addAttribute("label",labelService.selectById(movieService.selectById(id).getLabelid()));
        return "movie";
    }

    @GetMapping("/type/{typeid}/{pageNum}")
    public String getMovieByType(@PathVariable("typeid") Integer id , Model model,@PathVariable("pageNum") Integer pageNum){
        MovieFilter filter = new MovieFilter();
        filter.setLabelId(id);
        Integer totalPageNum =movieService.selectByFilter(filter).size()/12;
        if(pageNum-1<0){
            pageNum=1;
        }
        if (pageNum>totalPageNum){
            pageNum=totalPageNum;
        }
        filter.setOffset(12);
        filter.setRows((pageNum-1)*12);
        ArrayList<Movie> movieList = (ArrayList<Movie>)movieService.selectByFilter(filter);
        ArrayList<Label> lableList = (ArrayList<Label>)labelService.selectAll();
        model.addAttribute("movieList" , movieList);
        model.addAttribute("lableList" , lableList);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("totalPage",totalPageNum);
        model.addAttribute("typeid",id);
        return "index";
    }

    @GetMapping("/search/{movieName}")
    public String searchMovie(@PathVariable("movieName") String name ,Model model){
        MovieFilter filter = new MovieFilter();
        filter.setMovieName(name);
        ArrayList<Movie> movieList = (ArrayList<Movie>)movieService.selectByFilter(filter);
        ArrayList<Label> lableList = (ArrayList<Label>)labelService.selectAll();
        model.addAttribute("movieList" , movieList);
        model.addAttribute("lableList" , lableList);
        model.addAttribute("pageNum",1);
        model.addAttribute("totalPage",1);
        return "index";
    }
}
