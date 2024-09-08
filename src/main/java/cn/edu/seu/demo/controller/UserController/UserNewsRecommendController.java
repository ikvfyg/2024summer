package cn.edu.seu.demo.controller.UserController;

import cn.edu.seu.demo.pojo.*;
import cn.edu.seu.demo.service.UserService.UserNewsRecommendService;
import cn.edu.seu.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/news-recommend")
public class UserNewsRecommendController {
    @Autowired
    private UserNewsRecommendService userNewsRecommendService;

    /**
     * 获取所有分类
     */
    @PostMapping("/classify")
    public Result GetAllClassify() {
        log.info("获取全部分类");
        List<Classify> classifyList = userNewsRecommendService.GetAllClassify();
        return Result.success(classifyList);
    }

//    /**
//     * 设置偏好分类
//     */
//    @PutMapping("/preference-classify")
//    public Result SetPreferenceClassify(@RequestHeader("Authorization") String authorizationHeaderValue,@RequestBody List<Classify> classifyList){
//        log.info("设置偏好分类");
//        Claims claims= JwtUtils.parseJWT(authorizationHeaderValue);
//        // 从claims中获取userId
//        Integer userId = (Integer) claims.get("id");
//        userNewsRecommendService.SetPreferenceClassify(userId,classifyList);
//        return Result.success();
//    }

    /**
     * 获取某分类下某页推送
     */
    @PostMapping("/recommend")
    public Result GetOneClassifyRecommend(@RequestBody HashMap dict) {
        Integer classifyId = (Integer) dict.get("classify_id");
        Integer startId = (Integer) dict.get("startId");
        Integer pagesize = (Integer) dict.get("pagesize");
//        log.info("获取{}分类下{}页推送,{}每页",classifyId,page,pagesize);
//        PageBean pageBean=userNewsRecommendService.GetOneClassifyRecommend(classifyId,startId,pagesize);
        List<News> newsList=userNewsRecommendService.GetOneClassifyRecommend(classifyId, startId, pagesize);
        return Result.success(newsList);
    }

    /**
     * 获取用户全部分类下某页推送
     */
    @PostMapping("/recommend-all")
    public Result GetUserClassifyRecommend(@RequestHeader("Authorization") String authorizationHeaderValue, @RequestBody HashMap dict) {
        Claims claims = JwtUtils.parseJWT(authorizationHeaderValue);
        // 从claims中获取userId
        Integer userId = (Integer) claims.get("id");
        Integer pagesize = (Integer) dict.get("pagesize");
        Integer startId = (Integer) dict.get("start_id");
        List<News> newsList = userNewsRecommendService.GetUserClassifyRecommend(userId, startId, pagesize);
        return Result.success(newsList);
    }

    /**
     * 获取所有新闻源
     */
//    @PostMapping("/all-source")
//    public Result GetAllSource() {
//        log.info("获取所有新闻源");
//        List<Source> sourceList = userNewsRecommendService.GetAllSource();
//        return Result.success(sourceList);
//    }

    /**
     * 获取用户黑名单
     */
    @PostMapping("/get-black")
    public Result GetUserBlackList(@RequestHeader("Authorization") String authorizationHeaderValue) {
        Claims claims = JwtUtils.parseJWT(authorizationHeaderValue);
        // 从claims中获取userId
        Integer userId = (Integer) claims.get("id");
        List<String> blackList = userNewsRecommendService.GetUserBlackList(userId);
        return Result.success(blackList);
    }

    /**
     * 设置用户黑名单
     */
    @PostMapping("/set-black")
    public Result SetUserBlackList(@RequestHeader("Authorization") String authorizationHeaderValue, @RequestBody List<String> blackList) {
        Claims claims = JwtUtils.parseJWT(authorizationHeaderValue);
        // 从claims中获取userId
        Integer userId = (Integer) claims.get("id");
        userNewsRecommendService.SetUserBlackList(userId, blackList);
        return Result.success();
    }
}
