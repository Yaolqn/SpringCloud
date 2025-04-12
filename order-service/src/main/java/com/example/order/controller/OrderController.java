package com.example.order.controller;

import com.example.common.model.Result;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Result<com.example.common.model.Order> getOrderWithUser(@PathVariable("id") Long id) throws InterruptedException{
        Thread.sleep(4000);
        return orderService.getOrderWithUser(id);
    }
}