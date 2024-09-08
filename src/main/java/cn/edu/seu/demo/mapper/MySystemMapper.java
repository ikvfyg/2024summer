package cn.edu.seu.demo.mapper;

import cn.edu.seu.demo.pojo.RawNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MySystemMapper {
    @Select("select * from news where summary is null or summary = '' LIMIT #{size}")
    List<RawNews> GetNonSummarizedNews(Integer size);

    @Update("update news set summary=#{news.summary} where id=#{news.id}")
    void SetSummary(@Param("news") RawNews news);
}
