package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("name") String name,@RequestParam("password") String password){
        User user = userService.selectByName(name);
        if(user.getPassworld().equals(password)){
            return "redirect:/movie/list/2019";
        }
        return null;
    }

    @GetMapping("/toregister")
    public String toRegister(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String register(User user){
        System.out.println(user.getName());
        return "redirect:/movie/list/2019";
    }
}
