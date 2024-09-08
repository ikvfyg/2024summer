package cn.edu.seu.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawNews extends NoContentNews{
    private String content;//内容
}
