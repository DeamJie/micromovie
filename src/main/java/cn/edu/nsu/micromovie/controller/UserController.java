package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.UserService;
import cn.edu.nsu.micromovie.util.HandleResult;
import cn.edu.nsu.micromovie.util.security.CryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
        if(CryptUtil.decrypt(temp.getPassworld()).equals(user.getPassworld())){
            return HandleResult.success();
        }
        return HandleResult.error("登录失败，请检查用户名和密码！");
    }


    @PostMapping("/register")
    @ResponseBody
    public HandleResult register(@RequestBody User user){
        user.setPassworld(CryptUtil.encrypt(user.getPassworld()));
        Integer result = userService.createUser(user);
        if(result == 1){
            return HandleResult.success();
        }else {
            return HandleResult.error("注册失败");
        }
    }

    @GetMapping("/info")
    public String userInfo(Model model){
        return "/user";
    }

    @PostMapping("/update")
    @ResponseBody
    public HandleResult updateUser(@RequestBody User user){
        int result = userService.updateByPrimaryKey(user);
        if (result == 1){
            return HandleResult.success();
        }else {
            return HandleResult.error("保存修改信息错误！");
        }
    }

    @GetMapping("/exit")
    @ResponseBody
    public HandleResult exit(){
        if (true){
            return HandleResult.success();
        }else {
            return HandleResult.error();
        }
    }

    @PostMapping ("/isExit")
    @ResponseBody
    public Boolean isExit(@RequestBody String mail){
        String mailString = mail.split("=")[1];
        try {
            mailString= URLDecoder.decode(mailString,"utf-8");
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        return userService.isExit(mailString);
    }
}
