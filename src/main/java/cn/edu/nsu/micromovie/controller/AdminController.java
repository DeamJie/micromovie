package cn.edu.nsu.micromovie.controller;

import cn.edu.nsu.micromovie.model.Admin;
import cn.edu.nsu.micromovie.service.AdminService;
import cn.edu.nsu.micromovie.util.HandleResult;
import cn.edu.nsu.micromovie.util.security.CryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public HandleResult adminLogin(@RequestBody Admin admin){
        Admin temp = adminService.selectByName(admin.getName());
        if (temp == null){
            return HandleResult.error("没有此账号！");
        }
        else {
            if (admin.getPassworld().equals(CryptUtil.decrypt(temp.getPassworld()))){
                return HandleResult.success();
            }else {
                return HandleResult.error("密码错误！");
            }
        }
    }
}
