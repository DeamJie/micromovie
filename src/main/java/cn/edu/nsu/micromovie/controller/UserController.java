package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Boolean login(User user){
        User temp = userService.selectByName(user.getName());
        if(temp.getPassworld().equals(user.getPassworld())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    @PostMapping("/register")
    @ResponseBody
    public Boolean register(User user){
        Integer result = userService.createUser(user);
        if(result == 1){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    @PostMapping("/isExit")
    @ResponseBody
    public Boolean isExit(String mail){
        return !userService.isExit(mail);
    }
}
