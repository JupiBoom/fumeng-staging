package cn.xilikeli.staging.vo.order;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单 VO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class OrderVO {

    /**
     * 订单 ID
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户 ID
     */
    private Long accountId;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

    /**
     * 订单状态: 1-待支付, 2-已支付, 3-已发货, 4-已完成, 5-已取消
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}