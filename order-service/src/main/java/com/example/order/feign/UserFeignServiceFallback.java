package com.example.order.feign;

import com.example.common.model.Result;
import com.example.common.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignServiceFallback implements UserFeignService {
    @Override
    public Result<User> getUser(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("fallback user");
        return Result.success(user);
    }
}