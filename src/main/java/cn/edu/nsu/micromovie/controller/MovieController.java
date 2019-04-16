package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.Evaluation;
import cn.edu.nsu.micromovie.model.Label;
import cn.edu.nsu.micromovie.model.Movie;
import cn.edu.nsu.micromovie.service.EvaluationService;
import cn.edu.nsu.micromovie.service.LabelService;
import cn.edu.nsu.micromovie.service.MovieService;
import cn.edu.nsu.micromovie.service.UserService;
import cn.edu.nsu.micromovie.util.EvaluationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@RequestMapping("/movie")
@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private UserService userService;

    @RequestMapping("/{movieId}")
    public String movieData(@PathVariable("movieId") int movieId, Model model){
        Movie movie = movieService.getMovie(movieId);
        Label label = labelService.getLabelById(movie.getLabelid());
        ArrayList<Evaluation> evaluationList = (ArrayList<Evaluation>) evaluationService.getEvaluationOfMovie(movieId);
        ArrayList<EvaluationUtil> evaluations = new ArrayList<>();
        for (Evaluation e:evaluationList){
            evaluations.add(new EvaluationUtil(userService.getUser(e.getUid()).getName(),e.getEvaluation()));
        }
        model.addAttribute("movie",movie);
        model.addAttribute("evaluations",evaluations);
        model.addAttribute("label",label);
        model.addAttribute("num",evaluations.size());
        return "movie";
    }

    @RequestMapping("/evaluation")
    public String addEvaluation(@RequestParam("input") String input){
        System.out.println(input);
        return null;
    }
}
