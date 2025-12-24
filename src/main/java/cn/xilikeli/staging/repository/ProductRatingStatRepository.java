package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.ProductRatingStatDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品评分统计 Repository 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Repository
public interface ProductRatingStatRepository extends JpaRepository<ProductRatingStatDO, Long> {

    /**
     * 根据商品 ID 查询评分统计
     *
     * @param productId 商品 ID
     * @return 评分统计
     */
    ProductRatingStatDO findByProductId(Long productId);

}