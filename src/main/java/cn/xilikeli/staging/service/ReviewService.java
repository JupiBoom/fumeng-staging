package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.ReviewDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 评价 Service 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
public interface ReviewService {

    /**
     * 创建评价
     *
     * @param reviewDO 评价信息
     * @return 评价信息
     */
    ReviewDO createReview(ReviewDO reviewDO);

    /**
     * 根据 ID 查询评价
     *
     * @param reviewId 评价 ID
     * @return 评价信息
     */
    ReviewDO getReviewById(Long reviewId);

    /**
     * 根据商品 ID 查询评价列表
     *
     * @param productId 商品 ID
     * @return 评价列表
     */
    List<ReviewDO> getReviewListByProductId(Long productId);

    /**
     * 根据商品 ID 查询评价列表（分页）
     *
     * @param productId 商品 ID
     * @param pageable 分页参数
     * @return 评价列表
     */
    Page<ReviewDO> getReviewListByProductId(Long productId, Pageable pageable);

    /**
     * 根据用户 ID 查询评价列表
     *
     * @param accountId 用户 ID
     * @return 评价列表
     */
    List<ReviewDO> getReviewListByAccountId(Long accountId);

    /**
     * 根据订单 ID 查询评价
     *
     * @param orderId 订单 ID
     * @return 评价信息
     */
    ReviewDO getReviewByOrderId(Long orderId);

    /**
     * 根据商品 ID 和评价状态查询评价列表
     *
     * @param productId 商品 ID
     * @param status 评价状态
     * @return 评价列表
     */
    List<ReviewDO> getReviewListByProductIdAndStatus(Long productId, Integer status);

    /**
     * 更新评价信息
     *
     * @param reviewId 评价 ID
     * @param reviewDO 评价信息
     * @return 评价信息
     */
    ReviewDO updateReview(Long reviewId, ReviewDO reviewDO);

    /**
     * 删除评价
     *
     * @param reviewId 评价 ID
     */
    void deleteReview(Long reviewId);

}