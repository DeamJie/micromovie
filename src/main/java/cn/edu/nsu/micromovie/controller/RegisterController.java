package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register_view")
    public String registerView(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam("name") String name,@RequestParam("email") String email,
                           @RequestParam("phone") Long phone,@RequestParam("password") String password,
                           @RequestParam("repassword") int repassword){
        User user = new User();
        user.setName(name);
        user.setFreeze(0);
        user.setPhonenumber(phone);
        user.setMail(email);
        user.setPassworld(password);
        userService.register(user);
        return "index";
    }
}
