package cn.edu.seu.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryText {
    private Integer id;//ID
    private Integer historyId;//历史记录id
    private String question;//问题
    private String answer;//答案
    private LocalDateTime datetime;//时间
}
