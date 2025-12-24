package cn.xilikeli.staging.dto.review;

import lombok.Data;

/**
 * <p>
 * 评价 DTO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class ReviewDTO {

    /**
     * 用户 ID
     */
    private Long accountId;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 订单 ID
     */
    private Long orderId;

    /**
     * 评分(1-5)
     */
    private Integer rating;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价状态: 1-正常, 0-隐藏
     */
    private Integer status;

}