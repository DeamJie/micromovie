package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.UserService;
import cn.edu.nsu.micromovie.util.HandleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public HandleResult login(@RequestBody User user){
        User temp = userService.selectByEmail(user.getMail());
        if (temp == null){
            return HandleResult.error("登录失败，没有此用户！");
        }
        if(temp.getPassworld().equals(user.getPassworld())){
            return HandleResult.success();
        }
        return HandleResult.error("登录失败，请检查用户名和密码！");
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
