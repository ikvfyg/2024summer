package cn.edu.seu.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    //顺序可换？ 对
    private List rows;//当前页数据列表
    private Long total;//总记录数 用long
}

