package top.lzp.smslogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzp.smslogin.dao.UserMapper;
import top.lzp.smslogin.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public boolean findUserByPhoneAndPassword(User user) {
        User temp = userMapper.findUserByPhoneAndPassword(user);

        return temp==null?false:true;
    }

    @Override
    public boolean findUserByPhone(String phone) {
        User temp = userMapper.findUserByPhone(phone);
        return temp==null?true:false;
    }
}
