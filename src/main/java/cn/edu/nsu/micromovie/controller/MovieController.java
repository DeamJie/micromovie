package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.Filter.EvaluationFilter;
import cn.edu.nsu.micromovie.Filter.MovieFilter;
import cn.edu.nsu.micromovie.dao.CollectionMapper;
import cn.edu.nsu.micromovie.dao.MovieMapper;
import cn.edu.nsu.micromovie.dao.ScoreMapper;
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

import javax.jws.WebParam;
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
            List<Movie> recommend = movieService.selectLike(user.getPreference());
            if (recommend == null || recommend.size() == 0){
                recommend = movieMapper.selectByLabelId(movieService.selectById(id).getLabelid(),8);
                model.addAttribute("recommend",recommend);
            }else {
                model.addAttribute("recommend",movieService.selectLike(user.getPreference()).subList(0,8));
            }
        }
        model.addAttribute("list",list);
        model.addAttribute("label",labelService.selectById(movieService.selectById(id).getLabelid()));
        return "movie";
    }

    @GetMapping("/type/{typeid}/{pageNum}")
    public String getMovieByType(@PathVariable("typeid") Integer id , Model model,@PathVariable("pageNum") Integer pageNum ,HttpSession httpSession){
        MovieFilter filter = new MovieFilter();
        filter.setLabelId(id);
        Integer totalPageNum =movieService.selectByFilter(filter).size()/12;
        if(pageNum-1<0){
            pageNum=1;
        }
        if (pageNum>totalPageNum){
            pageNum=totalPageNum;
        }
        if (httpSession.getAttribute("user")!=null){
            User temp = (User)httpSession.getAttribute("user");
            if (temp.getPreference().getClickLabelId() == id){
                temp.getPreference().setClickLabelId(id);
                temp.getPreference().clickLabelScaleIncrease();
            }
            else {
                temp.getPreference().setClickLabelId(id);
                temp.getPreference().setClickLabelScale(0.1D);
                temp.getPreference().setConnectionLabelScale(0.2D);
                temp.getPreference().setScoreLabelScale(0.7D);
                temp.getPreference().clickLabelScaleIncrease();
            }
            httpSession.setAttribute("user",temp);
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

    @GetMapping("/del/{id}")
    @ResponseBody
    public HandleResult delMovie(@PathVariable("id") int id){
        if (movieService.del(id) ==1){
            return HandleResult.success();
        }else {
            return HandleResult.error("删除电影失败！");
        }
    }

    @GetMapping("/admin/select/{name}")
    public String selectAdmin(@PathVariable("name") String name , Model model){
        MovieFilter filter = new MovieFilter();
        filter.setMovieName(name);
        List<Movie> list = movieService.selectByFilter(filter);
        model.addAttribute("movieList" , list);
        model.addAttribute("pageNum",1);
        model.addAttribute("totalPage",1);
        return "admin/movie_list";
    }

    @GetMapping("/recommend/{pageNum}")
    public String getRecommend(HttpSession session,Model model,@PathVariable("pageNum") int pageNum){
        User temp = (User)session.getAttribute("user");
        List<Movie> movieList = movieService.selectLike(temp.getPreference());
        if (movieList==null||movieList.size()==0){
            return "redirect:/movie/list/2018/1";
        }
        else {
            ArrayList<Label> lableList = (ArrayList<Label>)labelService.selectAll();
            int totalPage=1;
            if (movieList.size()%12==0){
                totalPage = movieList.size()/12;
            }else {
                totalPage = movieList.size()/12+1;
            }
            if (pageNum<1){
                pageNum=1;
            }
            if (pageNum<totalPage){
                model.addAttribute("movieList",movieList.subList((pageNum-1)*12,pageNum*12));
            }
            if (pageNum>=totalPage){
                pageNum=totalPage;
                if (movieList.size()%12!=0){
                    model.addAttribute("movieList",movieList.subList((pageNum-1)*12,(pageNum-1)*12+(movieList.size()%12)));
                }
                else {
                    model.addAttribute("movieList",movieList.subList((pageNum-1)*12,pageNum*12));
                }
            }
            model.addAttribute("lableList" , lableList);
            model.addAttribute("pageNum",pageNum);
            model.addAttribute("totalPage",totalPage);
            return "recommend";
        }
    }
}
