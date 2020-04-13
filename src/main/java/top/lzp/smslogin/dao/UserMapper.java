package top.lzp.smslogin.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.lzp.smslogin.entity.User;
@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into user(phone,password) values(#{phone},#{password})")
    void save(User user);

    @Select("select * from user where phone=#{phone} and password = #{password}")
    User findUserByPhoneAndPassword(User user);

    @Select("select * from user where phone = #{phone}")
    User findUserByPhone(String phone);
}
