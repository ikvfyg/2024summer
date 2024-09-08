package cn.edu.seu.demo.mapper.UserMapper;

import cn.edu.seu.demo.pojo.Classify;
import cn.edu.seu.demo.pojo.NoContentNews;
import cn.edu.seu.demo.pojo.Source;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserNewsRecommendMapper {

    /**
     * 获取全部分类
     *
     * @return
     */
    @Select("select * from classify")
    List<Classify> GetAllClassify();

    /**
     * 设置偏好分类
     */

//    void SetPreferenceClassify(@Param("userId") Integer userId, @Param("classifyList") List<Classify> classifyList);

//    @Delete("delete from user_classify where user_id=#{userId}")
//    void RemoveAllPreferenceClassify(Integer userId);

    /**
     * 获取某分类下某页推送
     *
     * @param classifyId
     * @param pagesize
     * @return
     */
//    @Select("select * from news where classify_id=#{classifyId}")
    List<NoContentNews> GetOneClassifyRecommend(@Param("classifyId") Integer classifyId, @Param("startId") Integer startId, @Param("pagesize") Integer pagesize);

    /**
     * 获取全部新闻源
     *
     * @return
     */
    @Select("select * from source")
    List<Source> GetAllSource();

    /**
     * 获取用户偏好
     * @return
     */
//    @Select("select classify_id from user_classify where user_id=#{userId}")
//    List<Integer> GetUserClassify(Integer userId);

    /**
     * 根据偏好进行推送
     *
     * @return
     */
    List<NoContentNews> GetAllButBlackRecommend(@Param("filteredIds") List<Integer> filteredIds, @Param("startId") Integer startId, @Param("pagesize") Integer pagesize);

    /**
     * 获取用户黑名单
     *
     * @param userId
     * @return
     */
    @Select("select classify_id from black_list where user_id=#{userId}")
    List<Integer> GetUserBlackList(Integer userId);

    /**
     * 设置黑名单
     */
    void SetUserBlackList(@Param("userId") Integer userId, @Param("blackList") List<Integer> blackList);

    /**
     * 删除黑名单
     *
     * @param userId
     */
    @Delete("delete from black_list where user_id=#{userId}")
    void deleteUserBlackList(Integer userId);

//    List<NoContentNews> GetAllButBlackRecommendWithStart(@Param("filteredIds")List<Integer> filteredIds,@Param("startId") Integer startId,@Param("pagesize") Integer pagesize);

    /**
     *
     */
}
