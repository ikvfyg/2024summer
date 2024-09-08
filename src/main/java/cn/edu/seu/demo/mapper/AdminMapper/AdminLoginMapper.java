package cn.edu.seu.demo.mapper.AdminMapper;

import cn.edu.seu.demo.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminLoginMapper {
    @Select("select * from admin where username=#{username} and password=#{password}")
    Admin checkCredentials(String username, String password);
}
