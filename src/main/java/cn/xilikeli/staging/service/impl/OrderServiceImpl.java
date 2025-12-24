package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.model.OrderDO;
import cn.xilikeli.staging.repository.OrderRepository;
import cn.xilikeli.staging.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单 Service 实现类
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDO createOrder(OrderDO orderDO) {
        return orderRepository.save(orderDO);
    }

    @Override
    public OrderDO getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public OrderDO getOrderByOrderNo(String orderNo) {
        return orderRepository.findByOrderNo(orderNo);
    }

    @Override
    public Page<OrderDO> getOrderList(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public List<OrderDO> getOrderListByAccountId(Long accountId) {
        return orderRepository.findByAccountId(accountId);
    }

    @Override
    public OrderDO getOrderByAccountIdAndProductId(Long accountId, Long productId) {
        return orderRepository.findByAccountIdAndProductId(accountId, productId);
    }

    @Override
    public List<OrderDO> getOrderListByAccountIdAndStatus(Long accountId, Integer status) {
        return orderRepository.findByAccountIdAndStatus(accountId, status);
    }

    @Override
    public OrderDO updateOrder(Long orderId, OrderDO orderDO) {
        OrderDO existingOrder = orderRepository.findById(orderId).orElse(null);
        if (existingOrder != null) {
            existingOrder.setOrderNo(orderDO.getOrderNo());
            existingOrder.setAccountId(orderDO.getAccountId());
            existingOrder.setProductId(orderDO.getProductId());
            existingOrder.setQuantity(orderDO.getQuantity());
            existingOrder.setTotalPrice(orderDO.getTotalPrice());
            existingOrder.setStatus(orderDO.getStatus());
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}