package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.ProductRatingStatDO;

/**
 * <p>
 * 商品评分统计 Service 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
public interface ProductRatingStatService {

    /**
     * 创建商品评分统计
     *
     * @param productRatingStatDO 评分统计信息
     * @return 评分统计信息
     */
    ProductRatingStatDO createProductRatingStat(ProductRatingStatDO productRatingStatDO);

    /**
     * 根据商品 ID 查询评分统计
     *
     * @param productId 商品 ID
     * @return 评分统计信息
     */
    ProductRatingStatDO getProductRatingStatByProductId(Long productId);

    /**
     * 更新商品评分统计
     *
     * @param productId 商品 ID
     * @param productRatingStatDO 评分统计信息
     * @return 评分统计信息
     */
    ProductRatingStatDO updateProductRatingStat(Long productId, ProductRatingStatDO productRatingStatDO);

    /**
     * 删除商品评分统计
     *
     * @param productId 商品 ID
     */
    void deleteProductRatingStat(Long productId);

}