package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.model.OrderDO;
import cn.xilikeli.staging.model.ReviewDO;
import cn.xilikeli.staging.repository.OrderRepository;
import cn.xilikeli.staging.repository.ReviewRepository;
import cn.xilikeli.staging.service.ReviewService;
import cn.xilikeli.staging.vo.review.ReviewStatisticsVO;
import cn.xilikeli.staging.vo.review.ReviewVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品评价服务实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ReviewDO publishReview(Long productId, Long userId, Integer score, String content) {
        // 检查订单是否已完成
        OrderDO order = orderRepository.findByProductIdAndUserIdAndStatus(productId, userId, 2);
        if (order == null) {
            throw new RuntimeException("订单未完成，无法评价");
        }

        // 创建评价
        ReviewDO review = ReviewDO.builder()
                .productId(productId)
                .userId(userId)
                .score(score)
                .content(content)
                .reviewTime(new Date())
                .build();

        return reviewRepository.save(review);
    }

    @Override
    public Page<ReviewDO> getReviewsByProductId(Long productId, Pageable pageable) {
        return reviewRepository.findByProductId(productId, pageable);
    }

    @Override
    public Page<ReviewDO> getReviewsByUserId(Long userId, Pageable pageable) {
        return reviewRepository.findByUserId(userId, pageable);
    }

    @Override
    public Map<String, Object> getReviewStatisticsByProductId(Long productId) {
        Map<String, Object> statistics = new HashMap<>();

        // 总评价数
        long totalReviews = reviewRepository.countByProductId(productId);
        statistics.put("totalReviews", totalReviews);

        if (totalReviews == 0) {
            statistics.put("averageScore", 0);
            statistics.put("goodRate", 0);
            statistics.put("mediumRate", 0);
            statistics.put("badRate", 0);
            return statistics;
        }

        // 平均评分
        Double averageScore = reviewRepository.findAverageScoreByProductId(productId);
        statistics.put("averageScore", averageScore != null ? averageScore : 0);

        // 好评数量和比例
        long goodReviews = reviewRepository.countGoodReviewsByProductId(productId);
        double goodRate = (double) goodReviews / totalReviews * 100;
        statistics.put("goodRate", goodRate);

        // 中评数量和比例
        long mediumReviews = reviewRepository.countMediumReviewsByProductId(productId);
        double mediumRate = (double) mediumReviews / totalReviews * 100;
        statistics.put("mediumRate", mediumRate);

        // 差评数量和比例
        long badReviews = reviewRepository.countBadReviewsByProductId(productId);
        double badRate = (double) badReviews / totalReviews * 100;
        statistics.put("badRate", badRate);

        return statistics;
    }

}