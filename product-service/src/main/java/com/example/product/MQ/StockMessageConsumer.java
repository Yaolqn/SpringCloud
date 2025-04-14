package com.example.product.MQ;

import com.example.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "stock-topic", consumerGroup = "stock-log-group")
public class StockMessageConsumer {

    private final ProductMapper productMapper;

    // 监听库存变更消息

    public void logStockChange(Map<String, Object> message) {
        Long productId = (Long) message.get("productId");
        Integer amount = (Integer) message.get("amount");

        // 记录库存变更日志（实际项目可存ES或日志系统）
        System.out.printf("商品%d库存扣减%d%n", productId, amount);
    }
}