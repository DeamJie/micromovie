package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.Filter.EvaluationFilter;
import cn.edu.nsu.micromovie.Filter.MovieFilter;
import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.dto.EvaluationDto;
import cn.edu.nsu.micromovie.model.Label;
import cn.edu.nsu.micromovie.model.Movie;
import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.EvaluationService;
import cn.edu.nsu.micromovie.service.LabelService;
import cn.edu.nsu.micromovie.service.MovieService;
import cn.edu.nsu.micromovie.util.HandleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private LabelService labelService;
    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/all/{pageNum}")
    public String allMovie(Model model,@PathVariable("pageNum") Integer pageNum){
        MovieFilter filter = new MovieFilter();
        Integer totalPageNum =movieService.selectByFilter(filter).size()/8;
        if(pageNum-1<0){
            pageNum=1;
        }
        if (pageNum>totalPageNum){
            pageNum=totalPageNum;
        }
        filter.setOffset(8);
        filter.setRows((pageNum-1)*8);
        ArrayList<Movie> movieList = (ArrayList<Movie>)movieService.selectByFilter(filter);
        model.addAttribute("movieList" , movieList);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("totalPage",totalPageNum);
        return "admin/movie_list";
    }

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
    public String getMovie(@PathVariable("movieid") Integer id , Model model , HttpSession session){
        EvaluationFilter filter = new EvaluationFilter();
        filter.setMovieid(id);
        List<EvaluationDto> list = evaluationService.selectByMovieId(filter);
        model.addAttribute("movie",movieService.selectById(id));
        labelService.selectById(movieService.selectById(id).getLabelid());
        if (session.getAttribute("user")!=null){
            User user = (User)session.getAttribute("user");
            model.addAttribute("id",user.getId());
        }
        model.addAttribute("list",list);
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

    @GetMapping("/add-view")
    public String addView(){
        return "admin/movie_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public HandleResult addMovie(@RequestBody Movie movie){
        if (movieMapper.insertSelective(movie) ==1){
            return HandleResult.success();
        }else {
            return HandleResult.error("添加电影失败！");
        }
    }
}
