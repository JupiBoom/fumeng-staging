package cn.xilikeli.staging.dto.product;

import lombok.Data;
import java.math.BigDecimal;

/**
 * <p>
 * 商品 DTO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class ProductDTO {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 商品封面图 url
     */
    private String image;

    /**
     * 商品分类
     */
    private String category;

    /**
     * 商品状态: 1-上架, 0-下架
     */
    private Integer status;

}