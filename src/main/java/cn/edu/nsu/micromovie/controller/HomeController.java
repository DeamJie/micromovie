package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.model.Label;
import cn.edu.nsu.micromovie.model.Movie;
import cn.edu.nsu.micromovie.service.LabelService;
import cn.edu.nsu.micromovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private LabelService labelService;

    @RequestMapping("/index")
    public String index(Model model){
        ArrayList<Movie> movies = (ArrayList<Movie>) movieService.getMovieByTime(2018);
        ArrayList<Label> labels = (ArrayList<Label>) labelService.getLabel();
        model.addAttribute("movies",movies.subList(0,4));
        model.addAttribute("labels",labels);
        return "index";
    }

    @RequestMapping("/search")
    public String searchMovie(@RequestParam("movieName") String movieName, Model model){
        ArrayList<Label> labels = (ArrayList<Label>) labelService.getLabel();
        model.addAttribute("labels",labels);
        model.addAttribute("movies",movieService.getMovieByName(movieName));
        return "index";
    }

    @RequestMapping("/type/{typeName}")
    public String typeMovie(@PathVariable("typeName") String typeName,Model model){
        Label label = labelService.getLabelByName(typeName);
        System.out.println(typeName);
        ArrayList<Label> labels = (ArrayList<Label>) labelService.getLabel();
        model.addAttribute("labels",labels);
        ArrayList<Movie> movies = (ArrayList<Movie>) movieService.getMovieByLabel(label.getLabelid());
        model.addAttribute("movies",movies.subList(0,4));
        return "index";
    }

    @RequestMapping("/time/{time}")
    public String timeMovie(@PathVariable("time") int time,Model model){
        System.out.println(time);
        ArrayList<Label> labels = (ArrayList<Label>) labelService.getLabel();
        model.addAttribute("labels",labels);
        ArrayList<Movie> movies = (ArrayList<Movie>) movieService.getMovieByTime(time);
        model.addAttribute("movies",movies);
        return "index";
    }

    @RequestMapping("/old")
    public String oldMovie( Model model){
        ArrayList<Label> labels = (ArrayList<Label>) labelService.getLabel();
        model.addAttribute("labels",labels);
        ArrayList<Movie> movies = (ArrayList<Movie>) movieService.getOldMovie(2015);
        model.addAttribute("movies",movies.subList(0,4));
        return "index";
    }

    @RequestMapping("/new")
    public String nreMovie( Model model){
        ArrayList<Label> labels = (ArrayList<Label>) labelService.getLabel();
        model.addAttribute("labels",labels);
        ArrayList<Movie> movies = (ArrayList<Movie>) movieService.getNewMovie(2017);
        model.addAttribute("movies",movies.subList(0,4));
        return "index";
    }
}
