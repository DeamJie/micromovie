package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.AdminMapper;
import cn.edu.nsu.micromovie.model.Admin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Map<String,Object> login(String name , String passworld){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(name)){
            map.put("nameMsg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(passworld)){
            map.put("pwdMsg","密码不能为空");
            return map;
        }
        Admin admin = adminMapper.selectByName(name);
        if(admin==null){
            map.put("nameMsg","用户名不存在");
            return map;
        }
        if(admin.getPassworld()+admin.getSalt()!=passworld){
            map.put("pwdMsg","密码错误");
            return map;
        }
        //准备用作ticket
        map.put("success","登录成功");
        return map;
    }
    public Map<String,Object> register(String name , String passworld){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(name)){
            map.put("nameMsg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(passworld)){
            map.put("pwdMsg","密码不能为空");
            return map;
        }
        Admin admin = new Admin();
        admin.setName(name);
        admin.setPassworld(passworld);
        //这里怎么加salt呢？
        adminMapper.insertAdmin(admin);
        //预留的ticket
        map.put("success","登录成功");
        return map;
    }

}
