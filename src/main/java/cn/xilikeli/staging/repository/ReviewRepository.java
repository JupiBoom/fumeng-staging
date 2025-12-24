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
 * 评价 Repository 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Repository
public interface ReviewRepository extends JpaRepository<ReviewDO, Long> {

    /**
     * 根据商品 ID 查询评价列表
     *
     * @param productId 商品 ID
     * @return 评价列表
     */
    List<ReviewDO> findByProductId(Long productId);

    /**
     * 根据商品 ID 查询评价列表（分页）
     *
     * @param productId 商品 ID
     * @param pageable 分页参数
     * @return 评价列表
     */
    Page<ReviewDO> findByProductId(Long productId, Pageable pageable);

    /**
     * 根据用户 ID 查询评价列表
     *
     * @param accountId 用户 ID
     * @return 评价列表
     */
    List<ReviewDO> findByAccountId(Long accountId);

    /**
     * 根据订单 ID 查询评价
     *
     * @param orderId 订单 ID
     * @return 评价
     */
    ReviewDO findByOrderId(Long orderId);

    /**
     * 根据商品 ID 和评价状态查询评价列表
     *
     * @param productId 商品 ID
     * @param status 评价状态
     * @return 评价列表
     */
    List<ReviewDO> findByProductIdAndStatus(Long productId, Integer status);

}