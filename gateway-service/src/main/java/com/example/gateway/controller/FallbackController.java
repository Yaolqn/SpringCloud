package com.example.gateway.controller;

import com.example.common.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/fallback/order")
    public Result<String> orderFallback() {
        return Result.fail("订单服务暂时不可用，请稍后重试");
    }
}