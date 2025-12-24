package cn.xilikeli.staging.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;

/**
 * <p>
 * 评价表
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "review")
public class ReviewDO extends BaseDO {

    private static final long serialVersionUID = 1L;

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
     * 订单 ID
     */
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    /**
     * 评分(1-5)
     */
    @Column(name = "rating", nullable = false)
    private Integer rating;

    /**
     * 评价内容
     */
    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    /**
     * 评价状态: 1-正常, 0-隐藏
     */
    @Column(name = "status", nullable = false)
    private Integer status;

}