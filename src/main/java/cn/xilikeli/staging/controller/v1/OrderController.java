package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.model.OrderDO;
import cn.xilikeli.staging.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单控制器
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@RestController
@RequestMapping("/api/v1/orders")
@Api(tags = "订单管理")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ApiOperation("创建订单")
    public OrderDO createOrder(@RequestBody OrderDO orderDO) {
        return orderService.createOrder(orderDO);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询订单")
    public OrderDO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/order-no/{orderNo}")
    @ApiOperation("根据订单编号查询订单")
    public OrderDO getOrderByOrderNo(@PathVariable String orderNo) {
        return orderService.getOrderByOrderNo(orderNo);
    }

    @GetMapping
    @ApiOperation("查询订单列表")
    public Page<OrderDO> getOrderList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderService.getOrderList(pageable);
    }

    @GetMapping("/account/{accountId}")
    @ApiOperation("根据用户ID查询订单列表")
    public List<OrderDO> getOrderListByAccountId(@PathVariable Long accountId) {
        return orderService.getOrderListByAccountId(accountId);
    }

    @GetMapping("/account/{accountId}/product/{productId}")
    @ApiOperation("根据用户ID和商品ID查询订单")
    public OrderDO getOrderByAccountIdAndProductId(@PathVariable Long accountId, @PathVariable Long productId) {
        return orderService.getOrderByAccountIdAndProductId(accountId, productId);
    }

    @GetMapping("/account/{accountId}/status/{status}")
    @ApiOperation("根据用户ID和订单状态查询订单列表")
    public List<OrderDO> getOrderListByAccountIdAndStatus(@PathVariable Long accountId, @PathVariable Integer status) {
        return orderService.getOrderListByAccountIdAndStatus(accountId, status);
    }

    @PutMapping("/{id}")
    @ApiOperation("更新订单信息")
    public OrderDO updateOrder(@PathVariable Long id, @RequestBody OrderDO orderDO) {
        return orderService.updateOrder(id, orderDO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除订单")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

}