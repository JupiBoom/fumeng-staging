package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.model.ReviewDO;
import cn.xilikeli.staging.repository.ReviewRepository;
import cn.xilikeli.staging.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评价 Service 实现类
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDO createReview(ReviewDO reviewDO) {
        return reviewRepository.save(reviewDO);
    }

    @Override
    public ReviewDO getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public List<ReviewDO> getReviewListByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Page<ReviewDO> getReviewListByProductId(Long productId, Pageable pageable) {
        return reviewRepository.findByProductId(productId, pageable);
    }

    @Override
    public List<ReviewDO> getReviewListByAccountId(Long accountId) {
        return reviewRepository.findByAccountId(accountId);
    }

    @Override
    public ReviewDO getReviewByOrderId(Long orderId) {
        return reviewRepository.findByOrderId(orderId);
    }

    @Override
    public List<ReviewDO> getReviewListByProductIdAndStatus(Long productId, Integer status) {
        return reviewRepository.findByProductIdAndStatus(productId, status);
    }

    @Override
    public ReviewDO updateReview(Long reviewId, ReviewDO reviewDO) {
        ReviewDO existingReview = reviewRepository.findById(reviewId).orElse(null);
        if (existingReview != null) {
            existingReview.setAccountId(reviewDO.getAccountId());
            existingReview.setProductId(reviewDO.getProductId());
            existingReview.setOrderId(reviewDO.getOrderId());
            existingReview.setRating(reviewDO.getRating());
            existingReview.setContent(reviewDO.getContent());
            existingReview.setStatus(reviewDO.getStatus());
            return reviewRepository.save(existingReview);
        }
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}