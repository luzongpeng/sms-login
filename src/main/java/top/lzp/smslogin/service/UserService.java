package top.lzp.smslogin.service;

import top.lzp.smslogin.entity.User;

public interface UserService {
    void save(User user);

    boolean findUserByPhoneAndPassword(User user);

    boolean findUserByPhone(String phone);
}
