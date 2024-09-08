package cn.edu.seu.demo.service.UserService.impl;

import cn.edu.seu.demo.mapper.UserMapper.UserNewsSummarizeMapper;
import cn.edu.seu.demo.pojo.History;
import cn.edu.seu.demo.pojo.HistoryText;
import cn.edu.seu.demo.service.UserService.UserNewsSummarizeService;
import cn.edu.seu.demo.utils.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserNewsSummarizeServiceImpl implements UserNewsSummarizeService {
    @Autowired
    UserNewsSummarizeMapper userNewsSummarizeMapper;

    /**
     * 生成新闻摘要并保存
     *
     * @param content
     * @return
     */
    @Override
    public HashMap summarize(String content, Integer historyId,Integer userId) throws Exception {
        String result = ChatClient.llama3(content,userId);
        HistoryText historyText = new HistoryText();
        historyText.setHistoryId(historyId);
        historyText.setQuestion(content);
        historyText.setAnswer(result);
        LocalDateTime datetime =LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);;
        historyText.setDatetime(datetime);
        userNewsSummarizeMapper.AddHistory(historyText);
        Integer historyTextId = historyText.getId();
        HashMap response = new HashMap();
        response.put("content", result);
        response.put("history_id", historyId);
        response.put("history_text_id", historyTextId);
        response.put("datetime",datetime);
        return response;
    }

    @Override
    public List<Map<Integer,String>> GetUserHistory(Integer userId) {
        List<Map<Integer,String>> historyIds = userNewsSummarizeMapper.GetUserHistory(userId);
        return historyIds;
    }

    @Override
    public List<HistoryText> GetHistoryText(Integer historyId) {
        List<HistoryText> historyTextList = userNewsSummarizeMapper.GetHistoryText(historyId);
        return historyTextList;
    }

    @Override
    public HashMap resummarize(String content, Integer historyId, Integer historyTextId,Integer userId) throws Exception {
        String response = ChatClient.llama3(content,userId);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);;
        userNewsSummarizeMapper.UpdateHistory(response, historyId, historyTextId, now);
        HashMap result = new HashMap();
        result.put("history_id", historyId);
        result.put("history_text_id", historyTextId);
        result.put("content", response);
        result.put("datetime",now);
        return result;
    }

    @Override
    public History NewChat(Integer userId,String name) {
        LocalDateTime now=LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        History history=new History();
        history.setUserId(userId);
        history.setName(name);
        history.setStartTime(now);
        userNewsSummarizeMapper.NewChat(history);
        return history;
    }

    @Override
    public void PreRequest(Integer userId) throws Exception {
        ChatClient.prerequest(userId);
    }
}
