package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.OrderDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 订单 Service 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDO 订单信息
     * @return 订单信息
     */
    OrderDO createOrder(OrderDO orderDO);

    /**
     * 根据 ID 查询订单
     *
     * @param orderId 订单 ID
     * @return 订单信息
     */
    OrderDO getOrderById(Long orderId);

    /**
     * 根据订单编号查询订单
     *
     * @param orderNo 订单编号
     * @return 订单信息
     */
    OrderDO getOrderByOrderNo(String orderNo);

    /**
     * 查询订单列表（分页）
     *
     * @param pageable 分页参数
     * @return 订单列表
     */
    Page<OrderDO> getOrderList(Pageable pageable);

    /**
     * 根据用户 ID 查询订单列表
     *
     * @param accountId 用户 ID
     * @return 订单列表
     */
    List<OrderDO> getOrderListByAccountId(Long accountId);

    /**
     * 根据用户 ID 和商品 ID 查询订单
     *
     * @param accountId 用户 ID
     * @param productId 商品 ID
     * @return 订单信息
     */
    OrderDO getOrderByAccountIdAndProductId(Long accountId, Long productId);

    /**
     * 根据用户 ID 和订单状态查询订单列表
     *
     * @param accountId 用户 ID
     * @param status 订单状态
     * @return 订单列表
     */
    List<OrderDO> getOrderListByAccountIdAndStatus(Long accountId, Integer status);

    /**
     * 更新订单信息
     *
     * @param orderId 订单 ID
     * @param orderDO 订单信息
     * @return 订单信息
     */
    OrderDO updateOrder(Long orderId, OrderDO orderDO);

    /**
     * 删除订单
     *
     * @param orderId 订单 ID
     */
    void deleteOrder(Long orderId);

}