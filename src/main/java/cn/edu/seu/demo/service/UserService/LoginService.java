package cn.edu.seu.demo.service.UserService;

import cn.edu.seu.demo.pojo.User;


public interface LoginService {
    User checkCredentials(String phoneNumber, String password);

    User checkRebundant(String phoneNumber, String username);

    void register(User user);
}
