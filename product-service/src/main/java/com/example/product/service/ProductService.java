package com.example.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.model.Result;
import com.example.product.entity.Product;
import com.example.product.mapper.ProductMapper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {
    public Result<Product> getProductById(Long id) {
        Product product = this.getById(id);
        if (product == null) {
            return Result.fail("User not found");
        }
        return Result.success(product);
    }

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 扣减库存（带事务）
    @Transactional
    public Result<Product> deductStock(Long productId, Integer amount) {
        int rows = productMapper.deductStock(productId, amount);
        if (rows == 0) {
            throw new RuntimeException("库存不足");
        }

        // 发送库存变更消息
        rocketMQTemplate.convertAndSend(
                "stock-topic",
                Map.of("productId", productId, "amount", amount)
        );
        return Result.success(getById(productId));
    }
}