package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.dto.review.ReviewDTO;
import cn.xilikeli.staging.model.ReviewDO;
import cn.xilikeli.staging.service.ReviewService;
import cn.xilikeli.staging.vo.review.ReviewStatisticsVO;
import cn.xilikeli.staging.vo.review.ReviewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 商品评价控制器
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@RestController
@RequestMapping("/api/v1/reviews")
@Api(tags = "商品评价管理")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @ApiOperation("发表商品评价")
    @PostMapping
    public ReviewVO publishReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDO reviewDO = reviewService.publishReview(
                reviewDTO.getProductId(),
                reviewDTO.getUserId(),
                reviewDTO.getScore(),
                reviewDTO.getContent()
        );
        ReviewVO reviewVO = new ReviewVO();
        BeanUtils.copyProperties(reviewDO, reviewVO);
        return reviewVO;
    }

    @ApiOperation("根据商品ID查询评价列表")
    @GetMapping("/product/{productId}")
    public Page<ReviewVO> getReviewsByProductId(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "reviewTime,desc") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0]).ascending());
        if (sort.split(",")[1].equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0]).descending());
        }
        Page<ReviewDO> reviewDOPage = reviewService.getReviewsByProductId(productId, pageable);
        return reviewDOPage.map(reviewDO -> {
            ReviewVO reviewVO = new ReviewVO();
            BeanUtils.copyProperties(reviewDO, reviewVO);
            return reviewVO;
        });
    }

    @ApiOperation("根据用户ID查询评价历史")
    @GetMapping("/user/{userId}")
    public Page<ReviewVO> getReviewsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "reviewTime,desc") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0]).ascending());
        if (sort.split(",")[1].equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0]).descending());
        }
        Page<ReviewDO> reviewDOPage = reviewService.getReviewsByUserId(userId, pageable);
        return reviewDOPage.map(reviewDO -> {
            ReviewVO reviewVO = new ReviewVO();
            BeanUtils.copyProperties(reviewDO, reviewVO);
            return reviewVO;
        });
    }

    @ApiOperation("根据商品ID查询评价统计信息")
    @GetMapping("/statistics/{productId}")
    public ReviewStatisticsVO getReviewStatisticsByProductId(@PathVariable Long productId) {
        Map<String, Object> statisticsMap = reviewService.getReviewStatisticsByProductId(productId);
        ReviewStatisticsVO reviewStatisticsVO = new ReviewStatisticsVO();
        reviewStatisticsVO.setTotalReviews((Long) statisticsMap.get("totalReviews"));
        reviewStatisticsVO.setAverageScore((Double) statisticsMap.get("averageScore"));
        reviewStatisticsVO.setGoodRate((Double) statisticsMap.get("goodRate"));
        reviewStatisticsVO.setMediumRate((Double) statisticsMap.get("mediumRate"));
        reviewStatisticsVO.setBadRate((Double) statisticsMap.get("badRate"));
        return reviewStatisticsVO;
    }

}