package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.User;
import cn.edu.nsu.micromovie.service.UserService;
import cn.edu.nsu.micromovie.util.HandleResult;
import cn.edu.nsu.micromovie.util.security.CryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public HandleResult login(@RequestBody User user , HttpSession session){
        User temp = userService.selectByEmail(user.getMail());
        if (temp == null){
            return HandleResult.error("登录失败，没有此用户！");
        }
        if(CryptUtil.decrypt(temp.getPassworld()).equals(user.getPassworld())){
            session.setAttribute("user",temp);
            session.setMaxInactiveInterval(36000);
            return HandleResult.success();
        }
        return HandleResult.error("登录失败，请检查用户名和密码！");
    }


    @PostMapping("/register")
    @ResponseBody
    public HandleResult register(@RequestBody User user ,HttpSession session){
        user.setPassworld(CryptUtil.encrypt(user.getPassworld()));
        Integer result = userService.createUser(user);
        if(result == 1){
            User temp = userService.selectByEmail(user.getMail());
            session.setAttribute("user",temp);
            return HandleResult.success();
        }else {
            return HandleResult.error("注册失败");
        }
    }

    @GetMapping("/info")
    public String userInfo(Model model,HttpSession session){
        if (session.getAttribute("user")!=null){
            model.addAttribute("user",session.getAttribute("user"));
        }
        return "/user";
    }

    @PostMapping("/update")
    @ResponseBody
    public HandleResult updateUser(@RequestBody User user,HttpSession session){
        int result = userService.updateByPrimaryKey(user);
        if (result == 1){
            session.setAttribute("user",user);
            return HandleResult.success();
        }else {
            return HandleResult.error("保存修改信息错误！");
        }
    }

    @GetMapping("/exit")
    @ResponseBody
    public HandleResult exit(HttpSession session){
        if (session.getAttribute("user")!=null){
            session.removeAttribute("user");
            return HandleResult.success();
        }else {
            return HandleResult.error("当前尚未登录！");
        }
    }

    @GetMapping("/changePassword-prepare")
    public String passwordPrepare(){
        return "/pwd";
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public HandleResult updateUserPassword(@RequestBody User user,HttpSession session){
        User temp = (User)session.getAttribute("user");
        temp.setPassworld(CryptUtil.encrypt(user.getPassworld()));
        int result = userService.updateByPrimaryKey(temp);
        if (result == 1){
            session.setAttribute("user",temp);
            return HandleResult.success();
        }else {
            return HandleResult.error("修改密码失败！");
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
