package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.model.Result;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public Result<User> getUserById(Long id) {
        User user = this.getById(id);
        if (user == null) {
            return Result.fail("User not found");
        }
        return Result.success(user);
    }
}
