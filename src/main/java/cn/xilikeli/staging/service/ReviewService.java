package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.ReviewDO;
import cn.xilikeli.staging.vo.review.ReviewStatisticsVO;
import cn.xilikeli.staging.vo.review.ReviewVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * <p>
 * 商品评价服务接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
public interface ReviewService {

    /**
     * 发表商品评价
     * @param productId 商品ID
     * @param userId 用户ID
     * @param score 评分(1-5星)
     * @param content 评价内容
     * @return 评价信息
     */
    ReviewDO publishReview(Long productId, Long userId, Integer score, String content);

    /**
     * 根据商品ID查询评价列表
     * @param productId 商品ID
     * @param pageable 分页参数
     * @return 评价列表
     */
    Page<ReviewDO> getReviewsByProductId(Long productId, Pageable pageable);

    /**
     * 根据用户ID查询评价历史
     * @param userId 用户ID
     * @param pageable 分页参数
     * @return 评价历史
     */
    Page<ReviewDO> getReviewsByUserId(Long userId, Pageable pageable);

    /**
     * 根据商品ID查询评价统计信息
     * @param productId 商品ID
     * @return 评价统计信息
     */
    Map<String, Object> getReviewStatisticsByProductId(Long productId);

}