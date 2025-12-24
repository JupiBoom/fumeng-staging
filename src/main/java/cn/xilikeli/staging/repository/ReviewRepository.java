package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.ReviewDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品评价仓库接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Repository
public interface ReviewRepository extends JpaRepository<ReviewDO, Long> {

    /**
     * 根据商品ID查询评价列表
     * @param productId 商品ID
     * @param pageable 分页参数
     * @return 评价列表
     */
    Page<ReviewDO> findByProductId(Long productId, Pageable pageable);

    /**
     * 根据用户ID查询评价列表
     * @param userId 用户ID
     * @param pageable 分页参数
     * @return 评价列表
     */
    Page<ReviewDO> findByUserId(Long userId, Pageable pageable);

    /**
     * 根据商品ID统计评价数量
     * @param productId 商品ID
     * @return 评价数量
     */
    long countByProductId(Long productId);

    /**
     * 根据商品ID统计好评数量(评分>=4)
     * @param productId 商品ID
     * @return 好评数量
     */
    @Query("SELECT COUNT(*) FROM ReviewDO r WHERE r.productId = ?1 AND r.score >= 4")
    long countGoodReviewsByProductId(Long productId);

    /**
     * 根据商品ID统计中评数量(评分=3)
     * @param productId 商品ID
     * @return 中评数量
     */
    @Query("SELECT COUNT(*) FROM ReviewDO r WHERE r.productId = ?1 AND r.score = 3")
    long countMediumReviewsByProductId(Long productId);

    /**
     * 根据商品ID统计差评数量(评分<=2)
     * @param productId 商品ID
     * @return 差评数量
     */
    @Query("SELECT COUNT(*) FROM ReviewDO r WHERE r.productId = ?1 AND r.score <= 2")
    long countBadReviewsByProductId(Long productId);

    /**
     * 根据商品ID查询平均评分
     * @param productId 商品ID
     * @return 平均评分
     */
    @Query("SELECT AVG(r.score) FROM ReviewDO r WHERE r.productId = ?1")
    Double findAverageScoreByProductId(Long productId);

}