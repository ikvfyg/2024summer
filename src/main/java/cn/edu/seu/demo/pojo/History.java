package cn.edu.seu.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {
    private Integer id;
    private Integer userId;
    private LocalDateTime startTime;
    private String name;
}
