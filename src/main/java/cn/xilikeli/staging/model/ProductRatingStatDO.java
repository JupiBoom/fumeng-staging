package cn.xilikeli.staging.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p>
 * 商品评分统计表
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product_rating_stat")
public class ProductRatingStatDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 商品 ID
     */
    @Column(name = "product_id", nullable = false, unique = true)
    private Long productId;

    /**
     * 总评价数
     */
    @Column(name = "total_reviews", nullable = false)
    private Integer totalReviews;

    /**
     * 总评分
     */
    @Column(name = "total_rating", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalRating;

    /**
     * 平均评分
     */
    @Column(name = "average_rating", nullable = false, precision = 3, scale = 2)
    private BigDecimal averageRating;

    /**
     * 好评率
     */
    @Column(name = "positive_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal positiveRate;

}