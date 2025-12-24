package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.model.ReviewDO;
import cn.xilikeli.staging.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评价控制器
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@RestController
@RequestMapping("/api/v1/reviews")
@Api(tags = "评价管理")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ApiOperation("创建评价")
    public ReviewDO createReview(@RequestBody ReviewDO reviewDO) {
        return reviewService.createReview(reviewDO);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询评价")
    public ReviewDO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/product/{productId}")
    @ApiOperation("根据商品ID查询评价列表")
    public List<ReviewDO> getReviewListByProductId(@PathVariable Long productId) {
        return reviewService.getReviewListByProductId(productId);
    }

    @GetMapping("/product/{productId}/page")
    @ApiOperation("根据商品ID查询评价列表（分页）")
    public Page<ReviewDO> getReviewListByProductId(@PathVariable Long productId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewService.getReviewListByProductId(productId, pageable);
    }

    @GetMapping("/account/{accountId}")
    @ApiOperation("根据用户ID查询评价列表")
    public List<ReviewDO> getReviewListByAccountId(@PathVariable Long accountId) {
        return reviewService.getReviewListByAccountId(accountId);
    }

    @GetMapping("/order/{orderId}")
    @ApiOperation("根据订单ID查询评价")
    public ReviewDO getReviewByOrderId(@PathVariable Long orderId) {
        return reviewService.getReviewByOrderId(orderId);
    }

    @GetMapping("/product/{productId}/status/{status}")
    @ApiOperation("根据商品ID和评价状态查询评价列表")
    public List<ReviewDO> getReviewListByProductIdAndStatus(@PathVariable Long productId, @PathVariable Integer status) {
        return reviewService.getReviewListByProductIdAndStatus(productId, status);
    }

    @PutMapping("/{id}")
    @ApiOperation("更新评价信息")
    public ReviewDO updateReview(@PathVariable Long id, @RequestBody ReviewDO reviewDO) {
        return reviewService.updateReview(id, reviewDO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除评价")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

}