package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.OrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单 Repository 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderDO, Long> {

    /**
     * 根据用户 ID 查询订单列表
     *
     * @param accountId 用户 ID
     * @return 订单列表
     */
    List<OrderDO> findByAccountId(Long accountId);

    /**
     * 根据用户 ID 和商品 ID 查询订单
     *
     * @param accountId 用户 ID
     * @param productId 商品 ID
     * @return 订单
     */
    OrderDO findByAccountIdAndProductId(Long accountId, Long productId);

    /**
     * 根据用户 ID 和订单状态查询订单列表
     *
     * @param accountId 用户 ID
     * @param status 订单状态
     * @return 订单列表
     */
    List<OrderDO> findByAccountIdAndStatus(Long accountId, Integer status);

    /**
     * 根据订单编号查询订单
     *
     * @param orderNo 订单编号
     * @return 订单
     */
    OrderDO findByOrderNo(String orderNo);

}