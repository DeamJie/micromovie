package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.UserMapper;
import cn.edu.nsu.micromovie.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int createUser(User user){
        return userMapper.insertSelective(user);
    }

    public int updateByPrimaryKey(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectByName(String name){
        return userMapper.selectByName(name);
    }

    public boolean isExit(String mail){
        User user = userMapper.selectByMail(mail);
        if (user == null) return false;
        else return true;
    }
}
