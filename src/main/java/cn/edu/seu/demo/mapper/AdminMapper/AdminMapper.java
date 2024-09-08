package cn.edu.seu.demo.mapper.AdminMapper;

import cn.edu.seu.demo.pojo.History;
import cn.edu.seu.demo.pojo.HistoryText;
import cn.edu.seu.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select * from user")
    List<User> GetUserList();



    @Select("select * from history where user_id=#{userId} order by start_time desc")
    List<History> GetUserHistory(Integer userId);

    @Select("select * from history_text where history_id=#{historyId} order by datetime desc")
    List<HistoryText> GetUserHistoryItem(Integer historyId);
}
