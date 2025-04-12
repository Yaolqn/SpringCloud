package com.example.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.model.Result;
import com.example.common.model.User;
import com.example.order.entity.Order;
import com.example.order.feign.UserFeignService;
import com.example.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Autowired
    private UserFeignService userFeignService;

    public Result<com.example.common.model.Order> getOrderWithUser(Long id) {
        Order order = this.getById(id);
        if (order == null) {
            return Result.fail("Order not found");
        }

        com.example.common.model.Order orderDTO = new com.example.common.model.Order();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setOrderNo(order.getOrderNo());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setCreateTime(order.getCreateTime());
        orderDTO.setStatus(order.getStatus());

        // 调用用户服务获取用户信息
        Result<User> userResult = userFeignService.getUser(order.getUserId());
        if (userResult.getCode() == 200) {
            orderDTO.setUser(userResult.getData());
        }

        return Result.success(orderDTO);
    }
}