package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.OrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单仓库接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderDO, Long> {

    /**
     * 根据商品ID和用户ID查询订单
     * @param productId 商品ID
     * @param userId 用户ID
     * @return 订单信息
     */
    OrderDO findByProductIdAndUserId(Long productId, Long userId);

    /**
     * 根据商品ID和用户ID查询已完成的订单
     * @param productId 商品ID
     * @param userId 用户ID
     * @param status 订单状态(2-已完成)
     * @return 已完成的订单
     */
    OrderDO findByProductIdAndUserIdAndStatus(Long productId, Long userId, Integer status);

}