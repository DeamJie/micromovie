package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.Filter.CollectionFilter;
import cn.edu.nsu.micromovie.dao.CollectionMapper;
import cn.edu.nsu.micromovie.dao.ScoreMapper;
import cn.edu.nsu.micromovie.model.Collection;
import cn.edu.nsu.micromovie.model.Movie;
import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.CollectionService;
import cn.edu.nsu.micromovie.util.HandleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private CollectionMapper collectionMapper;

    @PostMapping("/{userId}")
    @ResponseBody
    public HandleResult createCollection(@PathVariable("userId") Integer userId, @RequestBody Collection collection, HttpSession session){
        collection.setUid(userId);
        if (collectionService.insert(collection) == 1){
            User temp = (User)session.getAttribute("user");
            Integer scoreLabel = scoreMapper.selectLike(temp.getId());
            Integer collectionLabel = collectionMapper.selectLike(temp.getId(),scoreLabel);
            if (scoreLabel!=null){
                temp.getPreference().setScoreLabelId(scoreLabel);
            }
            if (collectionLabel!=null){
                temp.getPreference().setConnectionLabelId(collectionLabel);
            }
            session.setAttribute("user",temp);
            return HandleResult.success();
        }else {
            return HandleResult.error("收藏电影失败!");
        }
    }

    @GetMapping("/list/{userId}/{pageNum}")
    public String getCollectionList(Model model, @PathVariable("userId") Integer userId , @PathVariable("pageNum") Integer pageNum){
        CollectionFilter filter = new CollectionFilter();
        filter.setUseId(userId);
        Integer totalPageNum = collectionService.selectByfilter(filter).size()/4;
        if (totalPageNum == 0){
            totalPageNum=1;
        }
        if(pageNum-1<0){
            pageNum=1;
        }
        if (pageNum>totalPageNum){
            pageNum=totalPageNum;
        }
        filter.setRows((pageNum-1)*4);
        filter.setOffset(4);
        List<Movie> list = collectionService.selectByfilter(filter);
        model.addAttribute("list",list);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("totalPage",totalPageNum);
        return "/moviecol";
    }

}
