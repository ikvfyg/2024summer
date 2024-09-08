package cn.edu.seu.demo.service.AdminService;


import cn.edu.seu.demo.pojo.Admin;

public interface LoginService {
    Admin checkCredentials(String username, String password);
}
