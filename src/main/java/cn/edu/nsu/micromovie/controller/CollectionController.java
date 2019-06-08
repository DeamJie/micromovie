package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.util.HandleResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/collection")
public class CollectionController {
    @PostMapping("/create")
    @ResponseBody
    public HandleResult createCollection(){
       return null;
    }

    @GetMapping("/list/{userId}")
    public String getCollectionList(Model model, @PathVariable("userId") Integer userId){
        return null;
    }


}
