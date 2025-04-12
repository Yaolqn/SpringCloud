package com.example.order.feign;

// 引入必要的类
import com.example.common.model.Result;
import com.example.common.model.User;
import org.springframework.stereotype.Component;

// 标记为Spring组件，由容器管理
@Component
// 实现Feign客户端接口(UserFeignService)
public class UserFeignServiceFallback implements UserFeignService {

    @Override
    public Result<User> getUser(Long id) {
        // 创建降级后的默认用户对象
        User user = new User();
        user.setId(-1L);          // 特殊ID标识降级数据
        user.setUsername("fallback user"); // 默认用户名

        // 返回统一响应格式的降级数据
        return Result.success(user);
    }
}