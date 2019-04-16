package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.UserMapper;
import cn.edu.nsu.micromovie.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Map<String,Object> register(User user){
        Map<String,Object> map = new HashMap<>();
        if(userMapper.selectByName(user.getName())!=null){
            map.put("nameMSG","该用户名已经存在");
            return map;
        }
        if(userMapper.selectByMail(user.getMail())!=null){
            map.put("nameMSG","该邮箱已经注册");
            return map;
        }
        if(StringUtils.isBlank(user.getName())){
            map.put("nameMSG","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getPassworld())){
            map.put("pwdMSG","密码不能为空");
            return map;
        }
        userMapper.insertUser(user);
        map.put("success","注册成功");
        return map;
    }

    public Map<String,Object> Login(String mail,String passworld){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(mail)){
            map.put("nameMSG","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(passworld)){
            map.put("pwdMSG","密码不能为空");
            return map;
        }
        if(passworld!=userMapper.selectByMail(mail).getPassworld()){
            map.put("pwdMSG","密码或者用户名错误");
            return map;
        }
        map.put("useId",userMapper.selectByMail(mail).getId());
        return map;
    }

    public User getUser(int id){
        return userMapper.selectById(id);
    }
}
