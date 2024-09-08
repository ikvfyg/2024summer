package cn.edu.seu.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoContentNews {
    private Integer id;//id
    private Integer classifyId;//分类ID
    private String title;//标题
    private LocalDateTime datetime;//时间
    private String url;//链接(pc)
    private String wapurl;//链接(pe)
    private Integer sourceId;//新闻源ID
    private String keywords;//关键字
    private String summary;//摘要

    public NoContentNews(@NotNull NoContentNews other) {
        this.id = other.getId();
        this.classifyId = other.getClassifyId();
        this.title = other.getTitle();
        this.datetime = other.getDatetime();
        this.url = other.getUrl();
        this.wapurl = other.getWapurl();
        this.sourceId = other.getSourceId();
        this.keywords = other.getKeywords();
        this.summary = other.getSummary();
    }
}
