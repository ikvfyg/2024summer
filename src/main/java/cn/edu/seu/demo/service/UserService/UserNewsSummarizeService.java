package cn.edu.seu.demo.service.UserService;

import cn.edu.seu.demo.pojo.History;
import cn.edu.seu.demo.pojo.HistoryText;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserNewsSummarizeService {
    HashMap summarize(String content, Integer historyId,Integer userId) throws Exception;

    List<Map<Integer,String>> GetUserHistory(Integer userId);

    List<HistoryText> GetHistoryText(Integer historyId);

    HashMap resummarize(String content, Integer historyId, Integer historyTextId,Integer userId) throws Exception;

    History NewChat(Integer userId, String name);

    void PreRequest(Integer userId) throws Exception;
}
