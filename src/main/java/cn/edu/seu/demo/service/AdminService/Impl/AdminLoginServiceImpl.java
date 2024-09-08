package cn.edu.seu.demo.service.AdminService.Impl;

import cn.edu.seu.demo.mapper.AdminMapper.AdminLoginMapper;
import cn.edu.seu.demo.pojo.Admin;
import cn.edu.seu.demo.service.AdminService.LoginService;
import cn.edu.seu.demo.utils.SHA256Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginServiceImpl implements LoginService {
    @Autowired
    private AdminLoginMapper loginMapper;

    @Override
    public Admin checkCredentials(String username, String password) {
        String sha256= SHA256Hashing.sha256(password);
        return loginMapper.checkCredentials(username, sha256);
    }
}
