package cn.edu.seu.demo.service.AdminService.Impl;

import cn.edu.seu.demo.mapper.AdminMapper.AdminDataMapper;
import cn.edu.seu.demo.mapper.UserMapper.UserNewsRecommendMapper;
import cn.edu.seu.demo.pojo.Classify;
import cn.edu.seu.demo.service.AdminService.AdminDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class AdminDataServiceImpl implements AdminDataService {
    @Autowired
    private AdminDataMapper adminDataMapper;
    @Autowired
    private UserNewsRecommendMapper userNewsRecommendMapper;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Map<String, Integer> GetNewsCount() {
        List<Map<Integer, Object>> list = adminDataMapper.GetNewsCount();
        Map<Integer, Integer> classifyIdToCount = list.stream().collect(Collectors.toMap(
                entry -> (Integer) entry.get("classify_id"),
                entry -> ((Long) entry.get("total_count")).intValue()
        ));
        List<Classify> dict = userNewsRecommendMapper.GetAllClassify();
        Map<String, Integer> classifyNameToId = dict.stream()
                .collect(Collectors.toMap(Classify::getClassifyName, Classify::getId));
        // 转换 Map<Integer, Integer> 到 Map<String, Integer> 使用流处理
        Map<String, Integer> nameToCountMap = classifyNameToId.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // classify_name
                        entry -> classifyIdToCount.getOrDefault(entry.getValue(), 0) // 获取对应的 count
                ));
        return nameToCountMap;
    }

    @Override
    public Integer GetAllNewsCount() {
        return adminDataMapper.GetAllNewsCount();
    }

    @Override
    public Integer GetUserCount() {
        return adminDataMapper.GetUserCount();
    }

    @Override
    public Integer GetChatCount() {
        return adminDataMapper.GetChatCount();
    }

    @Override
    public Map<LocalDate, Integer> GetNewsCountWeekly() {
        List<Map<String, Object>> results = adminDataMapper.getDailyNewsCounts();
        return results.stream().collect(Collectors.toMap(
                entry -> ((Date) entry.get("date")).toLocalDate(), // 将 java.sql.Date 转换为 LocalDate
                entry -> ((Long) entry.get("total_count")).intValue(),
                (existing, replacement) -> existing, // 如果有重复的键，保留现有值
                TreeMap::new // 使用 TreeMap 来按键排序（默认是从小到大的自然顺序）
        ));
    }

    @Override
    public Map<LocalDate, Integer> GetChatCountWeekly() {
        List<Map<String, Object>> results = adminDataMapper.getDailyChatCounts();
        return results.stream().collect(Collectors.toMap(
                entry -> ((Date) entry.get("date")).toLocalDate(), // 将 java.sql.Date 转换为 LocalDate
                entry -> ((Long) entry.get("total_count")).intValue(),
                (existing, replacement) -> existing, // 如果有重复的键，保留现有值
                TreeMap::new // 使用 TreeMap 来按键排序（默认是从小到大的自然顺序）
        ));
    }
}
