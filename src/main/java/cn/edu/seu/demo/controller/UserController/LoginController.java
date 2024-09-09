package cn.edu.seu.demo.controller.UserController;

import cn.edu.seu.demo.pojo.Result;
import cn.edu.seu.demo.pojo.User;
import cn.edu.seu.demo.service.UserService.LoginService;
import cn.edu.seu.demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 登录验证
     *
     * @param request
     * @param loginData
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody(required = false) User loginData, HttpServletRequest request) {
        log.info("登录尝试来自IP: " + request.getRemoteAddr());

        if (loginData == null) {
            log.warn("用户登录时请求体为空: " + request.getRemoteAddr());
            return Result.error("用户名或密码不能为空");
        }

        String phoneNumber = loginData.getPhoneNumber();
        String password = loginData.getPassword();

        //输入有效性检查
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            log.warn("用户名或密码为空: " + request.getRemoteAddr());
            return Result.error("用户名或密码不能为空");
        }
        if (phoneNumber.length() != 11 || password.length() < 6 || password.length() > 20) {
            return Result.error("用户名或密码错误");
        }

        //转到业务层验证
        User user = loginService.checkCredentials(phoneNumber, password);
        if (user != null) {
            //下发 JWT 令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("phoneNumber", user.getPhoneNumber());
            claims.put("username", user.getUsername());

            String jwt = JwtUtils.generateJwt(claims);  //缺陷: 此处如果user为学生, payload中不包含classId
            log.info("用户登录成功: " + user.getId());
            return Result.success(jwt);
        } else {
            log.warn("用户名或密码错误");
            return Result.error("用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public Result Register(@RequestBody User user, HttpServletRequest request) {
        log.info("登录尝试来自IP: " + request.getRemoteAddr());

        if (user == null) {
            log.warn("用户注册时请求体为空: " + request.getRemoteAddr());
            return Result.error("用户名或密码不能为空");
        }

        String phoneNumber = user.getPhoneNumber();
        String username = user.getUsername();
        String password = user.getPassword();

        //输入有效性检查
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            log.warn("用户名或密码为空: " + request.getRemoteAddr());
            return Result.error("用户名或密码不能为空");
        }
        if (phoneNumber.length() != 11 || password.length() < 6 || password.length() > 20) {
            return Result.error("手机号必须是11位，或密码必须为6-20位");
        }

        //转到业务层验证
        User thisuser = loginService.checkRebundant(phoneNumber, username);
        if (thisuser == null) {
            loginService.register(user);
            //下发 JWT 令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("role","user");
            claims.put("id", user.getId());
            claims.put("phoneNumber", user.getPhoneNumber());
            claims.put("username", user.getUsername());

            String jwt = JwtUtils.generateJwt(claims);  //缺陷: 此处如果user为学生, payload中不包含classId
            log.info("用户注册成功: " + user.getId());
            return Result.success(jwt);
        } else {
            log.warn("用户名或手机号已存在");
            return Result.error("用户名或手机号已存在");
        }
    }
}
