package cn.edu.nsu.micromovie.service;

import cn.edu.nsu.micromovie.dao.AdminMapper;
import cn.edu.nsu.micromovie.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public Admin selectByName(String name){
        return adminMapper.selectByName(name);
    }
}
