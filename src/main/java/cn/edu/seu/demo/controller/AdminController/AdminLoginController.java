package cn.edu.seu.demo.controller.AdminController;

import cn.edu.seu.demo.pojo.Admin;
import cn.edu.seu.demo.pojo.Result;
import cn.edu.seu.demo.service.AdminService.LoginService;
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
@RequestMapping("/admin/login")
public class AdminLoginController {
    @Autowired
    LoginService loginService;
    /**
     * 登录验证
     * @param request
     * @param loginData
     * @return
     */
    @PostMapping
    public Result login(@RequestBody(required = false) Admin loginData, HttpServletRequest request){
        log.info("登录尝试来自IP: " + request.getRemoteAddr());

        if(loginData == null) {
            log.warn("用户登录时请求体为空: " + request.getRemoteAddr());
            return Result.error("用户名或密码不能为空");
        }

        String username = loginData.getUsername();
        String password = loginData.getPassword();

        //输入有效性检查
        if(username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()){
            log.warn("用户名或密码为空: " + request.getRemoteAddr());
            return Result.error("用户名或密码不能为空");
        }

        //转到业务层验证
        Admin admin = loginService.checkCredentials(username, password);
        if(admin != null){
            //下发 JWT 令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", admin.getId());
            claims.put("username", admin.getUsername());

            String jwt = JwtUtils.generateJwt(claims);  //缺陷: 此处如果user为学生, payload中不包含classId
            log.info("用户登录成功: " + admin.getId());
            return Result.success(jwt);
        }

        else {
            log.warn("用户名或密码错误");
            return Result.error("用户名或密码错误");
        }
    }
}
