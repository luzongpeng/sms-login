package top.lzp.smslogin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Lu
 * @date 2020/4/13:11:59:12
 * @description
 */
@Mapper
@Repository
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String phone;
    private String password;
}
