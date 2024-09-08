package cn.edu.seu.demo.controller.AdminController;


import cn.edu.seu.demo.pojo.Result;
import cn.edu.seu.demo.service.AdminService.AdminDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/data")
public class AdminDataController {
    @Autowired
    private AdminDataService adminDataService;

    /**
     * 获取全部新闻数
     *
     * @return
     */
    @PostMapping("/all-news")
    public Result GetAllNewsCount() {
        Integer allNewsCount = adminDataService.GetAllNewsCount();
        return Result.success(allNewsCount);
    }

    /**
     * 获取各分类新闻数
     *
     * @return
     */
    @PostMapping("/news")
    public Result GetNewsCount() {
        Map<String, Integer> map = adminDataService.GetNewsCount();
        return Result.success(map);
    }

    /**
     * 获取用户数
     */
    @PostMapping("/user")
    public Result GetUserCount() {
        Integer userCount = adminDataService.GetUserCount();
        return Result.success(userCount);
    }

    /**
     * 获取chat数
     */
    @PostMapping("/chat")
    public Result GetChatCount(){
        Integer chatCount=adminDataService.GetChatCount();
        return Result.success(chatCount);
    }

    /**
     * 获取近7天新闻量
     */
    @PostMapping("/news-weekly")
    public Result GetNewsCountWeekly(){
        Map<LocalDate, Integer> map=adminDataService.GetNewsCountWeekly();
        return Result.success(map);
    }

    /**
     * 获取近7天chat量
     */
    @PostMapping("/chat-weekly")
    public Result GetChatCountWeekly(){
        Map<LocalDate, Integer> map=adminDataService.GetChatCountWeekly();
        return Result.success(map);
    }
}
