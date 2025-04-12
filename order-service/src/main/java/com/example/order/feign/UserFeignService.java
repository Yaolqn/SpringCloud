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