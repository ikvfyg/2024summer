package cn.edu.seu.demo.service.UserService.impl;

import cn.edu.seu.demo.mapper.UserMapper.LoginMapper;
import cn.edu.seu.demo.pojo.User;
import cn.edu.seu.demo.service.UserService.LoginService;
import cn.edu.seu.demo.utils.SHA256Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public User checkCredentials(String phoneNumber, String password) {
        return loginMapper.checkCredentials(phoneNumber,SHA256Hashing.sha256(password));
    }

    @Override
    public User checkRebundant(String phoneNumber, String username){
        return loginMapper.checkRebundant(phoneNumber,SHA256Hashing.sha256(username));
    }

    @Override
    public void register(User user) {
        user.setPassword(SHA256Hashing.sha256(user.getPassword()));
        loginMapper.register(user);
    }
}
