package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.Label;
import cn.edu.nsu.micromovie.service.LabelService;
import cn.edu.nsu.micromovie.util.HandleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping("/list")
    public String labelList(Model model){
        List<Label> list = labelService.selectAll();
        model.addAttribute("list",list);
        return "admin/tag_list";
    }

    @GetMapping("/add-view")
    public String addView(){
        return "admin/tag_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public HandleResult addLabel(@RequestBody Label label){
        if(labelService.insert(label)==1){
            return HandleResult.success();
        }else {
            return HandleResult.error("新建标签失败！");
        }
    }

    @PostMapping("/isExit")
    @ResponseBody
    public boolean isExit(String name){
        return !labelService.isExit(name);
    }
}
