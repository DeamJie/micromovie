package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.dao.UserMapper;
import cn.edu.nsu.micromovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login_view")
    public String loginView(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("contact") String email,@RequestParam("password") String password){
        HashMap<String,Object> map = (HashMap<String, Object>) userService.Login(email,password);
        return "index";
    }
}
