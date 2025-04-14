package com.example.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    // 自定义SQL示例：扣减库存
    @Update("UPDATE t_product SET stock = stock - #{amount} WHERE id = #{id} AND stock >= #{amount}")
    int deductStock(@Param("id") Long productId, @Param("amount") Integer amount);
}
