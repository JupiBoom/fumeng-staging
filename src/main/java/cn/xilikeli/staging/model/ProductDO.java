package cn.xilikeli.staging.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
public class ProductDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * 商品描述
     */
    @Column(name = "description", nullable = false, length = 2000)
    private String description;

    /**
     * 商品价格
     */
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * 商品库存
     */
    @Column(name = "stock", nullable = false)
    private Integer stock;

    /**
     * 商品封面图 url
     */
    @Column(name = "image", nullable = false, length = 255)
    private String image;

    /**
     * 商品分类
     */
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    /**
     * 商品状态: 1-上架, 0-下架
     */
    @Column(name = "status", nullable = false)
    private Integer status;

}