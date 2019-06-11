package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.Admin;
import cn.edu.nsu.micromovie.service.AdminService;
import cn.edu.nsu.micromovie.util.HandleResult;
import cn.edu.nsu.micromovie.util.security.CryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/login-view")
    public String loginView(){
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public HandleResult adminLogin(@RequestBody Admin admin , HttpSession session){
        Admin temp = adminService.selectByName(admin.getName());
        if (temp == null){
            return HandleResult.error("没有此账号！");
        }
        else {
            if (admin.getPassworld().equals(CryptUtil.decrypt(temp.getPassworld()))){
                session.setAttribute("admin",temp);
                session.setMaxInactiveInterval(36000);
                return HandleResult.success();
            }else {
                return HandleResult.error("密码错误！");
            }
        }
    }

    @PostMapping("/login-out")
    @ResponseBody
    public HandleResult adminLoginOut(HttpSession session){
        if (session.getAttribute("admin") == null){
            return HandleResult.error("注销登录失败");
        }else {
            session.removeAttribute("admin");
            return HandleResult.success();
        }
    }

    @GetMapping("/add-view")
    public String addView(){
        return "admin/admin_add";
    }

    @PostMapping("add")
    @ResponseBody
    public HandleResult addAdmin(@RequestBody Admin admin){
        Admin temp = adminService.selectByName(admin.getName());
        if (temp == null){
            admin.setPassworld(CryptUtil.encrypt(admin.getPassworld()));
            if (adminService.insert(admin)==1){
                return HandleResult.success();
            }else {
                return HandleResult.error("添加管理员失败");
            }
        }else {
            return HandleResult.error("当前管理员名称已被占用");
        }
    }

    @GetMapping("pwd-view")
    public String pwdView(){
        return "admin/pwd";
    }

    @PostMapping("pwd")
    @ResponseBody
    public HandleResult pwd(@RequestBody Admin admin){
        Admin temp = adminService.selectByName(admin.getName());
        if (admin.getOldPassworld().equals(CryptUtil.decrypt(temp.getPassworld()))){
            temp.setPassworld(CryptUtil.encrypt(admin.getPassworld()));
            if (adminService.pwd(temp) == 1){
                return HandleResult.success();
            }else {
                return HandleResult.error("修改密码失败");
            }
        }else {
            return HandleResult.error("旧的密码输入错误");
        }
    }
}
