package cn.xilikeli.staging.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order")
public class OrderDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @Column(name = "order_no", nullable = false, length = 32, unique = true)
    private String orderNo;

    /**
     * 用户 ID
     */
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    /**
     * 商品 ID
     */
    @Column(name = "product_id", nullable = false)
    private Long productId;

    /**
     * 购买数量
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * 订单总价
     */
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    /**
     * 订单状态: 1-待支付, 2-已支付, 3-已发货, 4-已完成, 5-已取消
     */
    @Column(name = "status", nullable = false)
    private Integer status;

}