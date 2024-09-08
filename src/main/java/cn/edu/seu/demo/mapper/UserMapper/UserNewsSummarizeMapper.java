package cn.edu.seu.demo.mapper.UserMapper;

import cn.edu.seu.demo.pojo.History;
import cn.edu.seu.demo.pojo.HistoryText;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserNewsSummarizeMapper {
    @Insert("insert into history_text (history_id, question, answer, datetime) VALUES (#{historyId},#{question},#{answer},#{datetime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void AddHistory(HistoryText historyText);

    @Select("select id,name from history where user_id=#{userId}")
    List<Map<Integer,String>> GetUserHistory(Integer userId);

    @Select("select * from history_text where history_id=#{historyId} ORDER BY datetime;")
    List<HistoryText> GetHistoryText(Integer historyId);

    @Update("update history_text set answer=#{answer},datetime=#{datetime} where history_id=#{historyId} and id=#{historyTextId}")
    void UpdateHistory(@Param("answer")String result, @Param("historyId")Integer historyId, @Param("historyTextId")Integer historyTextId, @Param("datetime")LocalDateTime now);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into history (user_id, start_time, name) VALUES (#{userId}, #{startTime}, #{name})")
    void NewChat(History history);
}
