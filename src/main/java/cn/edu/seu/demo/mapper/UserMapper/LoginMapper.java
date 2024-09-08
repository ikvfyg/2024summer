package cn.edu.seu.demo.mapper.UserMapper;

import cn.edu.seu.demo.pojo.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface LoginMapper {
    @Select("select * from user where phone_number=#{phoneNumber} and password=#{password}")
    User checkCredentials(@Param(("phoneNumber")) String phoneNumber, @Param("password") String password);

    @Select("select * from user where phone_number=#{phoneNumber} or username=#{username}")
    User checkRebundant(@Param("phoneNumber") String phoneNumber, @Param("username") String username);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user (username, phone_number, password) VALUES (#{username},#{phoneNumber},#{password})")
    void register(User user);
}
