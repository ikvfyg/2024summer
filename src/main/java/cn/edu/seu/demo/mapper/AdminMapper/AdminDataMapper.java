package cn.edu.seu.demo.mapper.AdminMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDataMapper {


    @Select("select count(*) from news")
    Integer GetAllNewsCount();

    @Select("SELECT classify_id, COUNT(*) AS total_count FROM news GROUP BY classify_id")
    List<Map<Integer, Object>> GetNewsCount();

    @Select("select count(*) from user")
    Integer GetUserCount();

    @Select("select count(*) from history_text")
    Integer GetChatCount();

    @Select("SELECT DATE(datetime) AS date, COUNT(*) AS total_count " +
            "FROM news " +
            "WHERE datetime >= NOW() - INTERVAL 7 DAY " +
            "GROUP BY DATE(datetime) " +
            "ORDER BY DATE(datetime)")
    List<Map<String, Object>> getDailyNewsCounts();

    @Select("SELECT DATE(datetime) AS date, COUNT(*) AS total_count " +
            "FROM history_text " +
            "WHERE datetime >= NOW() - INTERVAL 7 DAY " +
            "GROUP BY DATE(datetime) " +
            "ORDER BY DATE(datetime) ")
    List<Map<String, Object>> getDailyChatCounts();
}
