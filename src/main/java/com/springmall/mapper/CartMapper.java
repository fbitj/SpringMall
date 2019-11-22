package com.springmall.mapper;

import com.springmall.bean.Cart;
import com.springmall.bean.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    Cart selectByProductId(Integer productId);

    Cart selectByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

    Cart selectByUserIdAndProductIdAndDeleted(@Param("userId") Integer userId, @Param("productId")Integer productId, @Param("deleted") boolean deleted);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);


    /**
     * 逻辑删除购物车
     * @param id
     * @return
     */
    int logicDeleteCartById(Integer id);
    int logicDeleteCartByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

    int updateByProductIdSelective(int productId);
}
