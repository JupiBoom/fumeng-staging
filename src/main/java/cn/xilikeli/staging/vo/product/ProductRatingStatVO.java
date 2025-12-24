package cn.xilikeli.staging.vo.product;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品评分统计 VO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class ProductRatingStatVO {

    /**
     * 统计 ID
     */
    private Long id;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 总评价数
     */
    private Integer totalReviews;

    /**
     * 总评分
     */
    private BigDecimal totalRating;

    /**
     * 平均评分
     */
    private BigDecimal averageRating;

    /**
     * 好评率
     */
    private BigDecimal positiveRate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}