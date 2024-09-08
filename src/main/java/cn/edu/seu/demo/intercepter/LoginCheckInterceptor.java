package cn.edu.seu.demo.intercepter;

import cn.edu.seu.demo.pojo.Result;
import cn.edu.seu.demo.utils.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override//目标资源方法运行前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...");
        //1.获取请求url
        String url=request.getRequestURL().toString();
        log.info("url:{}",url);
        //2.判断url是否包含login 不需要
        //3.获取请求头中的token
        String jwt=request.getHeader("Authorization");
        //4.判断jwt是否为null或空串
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error=Result.error("NOT_LOGIN");
            //手动转换为json
            String notLogin= JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //5.解析token
        try {
            JwtUtils.parseJWT(jwt);
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error=Result.error("NOT_LOGIN");
            //手动转换为json
            String notLogin=JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //6.放行
        log.info("令牌合法，放行");
        return true;
    }

}
