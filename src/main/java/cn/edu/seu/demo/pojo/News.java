package cn.edu.seu.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News extends NoContentNews{
    private String classifyName;//分类名
    private String sourceName;//新闻源名
    public News(NoContentNews father, String classifyStr, String sourceStr) {
        // 调用父类的构造函数
        super(father);

        // 初始化子类的字段
        this.classifyName = classifyStr;
        this.sourceName = sourceStr;
    }
}
