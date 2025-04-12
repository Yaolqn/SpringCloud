package com.example.order.feign;

import com.example.common.model.Result;
import com.example.common.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", fallback = UserFeignServiceFallback.class)
public interface UserFeignService {
    @GetMapping("/user/{id}")
    Result<User> getUser(@PathVariable("id") Long id);
}


//participant O as order-service
//participant F as Feign代理
//participant U as user-service
//
//O->>F: 调用userService.getUser(1L)
//F->>U: 发送GET /user/1
//alt 正常情况
//U-->>F: 返回用户数据
//F-->>O: Result<User>
//    else 服务故障
//F-->>O: 执行UserFeignServiceFallback
//        end