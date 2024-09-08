package cn.edu.seu.demo.controller.UserController;

import cn.edu.seu.demo.pojo.History;
import cn.edu.seu.demo.pojo.HistoryText;
import cn.edu.seu.demo.pojo.Result;
import cn.edu.seu.demo.service.UserService.UserNewsSummarizeService;
import cn.edu.seu.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user/news-summarize")
public class UserNewsSummarizeController {
    @Autowired
    private UserNewsSummarizeService userNewsSummarizeService;

    /**
     * 预请求
     */
    @PostMapping("/pre-request")
    public Result PreRequest(@RequestHeader("Authorization") String authorizationHeaderValue) throws Exception {
        Claims claims = JwtUtils.parseJWT(authorizationHeaderValue);
        // 从claims中获取userId
        Integer userId = (Integer) claims.get("id");
        userNewsSummarizeService.PreRequest(userId);
        return Result.success();
    }

    /**
     * 新闻摘要生成
     *
     * @param dict
     * @return
     */
    @PostMapping("/generate")
    public Result NewsSummaryGenerate(@RequestHeader("Authorization") String authorizationHeaderValue,@RequestBody HashMap dict) throws Exception {
        String content = (String) dict.get("content");
        Integer historyId = (Integer) dict.get("history_id");
        Claims claims = JwtUtils.parseJWT(authorizationHeaderValue);
        // 从claims中获取userId
        Integer userId = (Integer) claims.get("id");
        HashMap summary = userNewsSummarizeService.summarize(content, historyId,userId);
        return Result.success(summary);
    }

    /**
     * 新闻摘要刷新
     */
    @PutMapping("/regenerate")
    public Result NewsSummaryRegenerate(@RequestHeader("Authorization") String authorizationHeaderValue,@RequestBody HashMap dict) throws Exception {
        String content = (String) dict.get("content");
        Integer historyId = (Integer) dict.get("history_id");
        Integer historyTextId = (Integer) dict.get("history_text_id");
        Claims claims = JwtUtils.parseJWT(authorizationHeaderValue);
        // 从claims中获取userId
        Integer userId = (Integer) claims.get("id");
        HashMap summary = userNewsSummarizeService.resummarize(content, historyId,historyTextId,userId);
        return Result.success(summary);
    }

    /**
     * 获取历史记录
     */
    @PostMapping("/history")
    public Result GetUserHistory(@RequestHeader("Authorization") String authorizationHeaderValue) {
        Claims claims = JwtUtils.parseJWT(authorizationHeaderValue);
        // 从claims中获取userId
        Integer userId = (Integer) claims.get("id");
        List<Map<Integer,String>> historyIds = userNewsSummarizeService.GetUserHistory(userId);
        return Result.success(historyIds);
    }

    /**
     * 获取历史记录条目
     */
    @PostMapping("/historyText")
    public Result GetHistoryText(@RequestBody HashMap historyIdMap) {
        Integer historyId = (Integer) historyIdMap.get("historyId");
        List<HistoryText> historyTextList = userNewsSummarizeService.GetHistoryText(historyId);
        return Result.success(historyTextList);
    }

    /**
     * 创建对话
     */
    @PostMapping("/new-chat")
    public Result NewChat(@RequestHeader("Authorization") String authorizationHeaderValue,@RequestBody HashMap map){
        Claims claims = JwtUtils.parseJWT(authorizationHeaderValue);
        // 从claims中获取userId
        Integer userId = (Integer) claims.get("id");
        String name= (String) map.get("name");
        History chat=userNewsSummarizeService.NewChat(userId,name);
        return Result.success(chat);
    }
}
