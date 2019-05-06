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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private LabelService labelService;

    @GetMapping("/list/{time}")
    public String movieList(@PathVariable("time") String time, Model model){
        MovieFilter filter = new MovieFilter();
        filter.setTime(Integer.valueOf(time));
        ArrayList<Movie> movieList = (ArrayList<Movie>)movieService.selectByFilter(filter);
        ArrayList<Label> lableList = (ArrayList<Label>)labelService.selectAll();
        model.addAttribute("movieList" , movieList);
        model.addAttribute("lableList" , lableList);
        return "index";
    }

    @GetMapping("/{movieid}")
    public String movie(@PathVariable("movieid") Integer id , Model model){
        model.addAttribute("movie",movieService.selectById(id));
        labelService.selectById(movieService.selectById(id).getLabelid());
        model.addAttribute("label",labelService.selectById(movieService.selectById(id).getLabelid()));
        return "movie";
    }
}
