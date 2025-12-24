package cn.xilikeli.staging;

import cn.xilikeli.staging.model.OrderDO;
import cn.xilikeli.staging.model.ReviewDO;
import cn.xilikeli.staging.repository.OrderRepository;
import cn.xilikeli.staging.repository.ReviewRepository;
import cn.xilikeli.staging.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 商品评价测试类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@SpringBootTest
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testPublishReview() {
        // 创建一个已完成的订单
        OrderDO order = OrderDO.builder()
                .productId(1L)
                .userId(1L)
                .status(2)
                .finishTime(new Date())
                .build();
        orderRepository.save(order);

        // 发表评价
        ReviewDO review = reviewService.publishReview(1L, 1L, 5, "商品质量非常好，物流也很快！");
        System.out.println("发表评价成功：" + review);
    }

    @Test
    public void testGetReviewsByProductId() {
        // 查询商品1的评价列表
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reviewTime").descending());
        Page<ReviewDO> reviews = reviewService.getReviewsByProductId(1L, pageable);
        System.out.println("商品1的评价列表：" + reviews.getContent());
    }

    @Test
    public void testGetReviewsByUserId() {
        // 查询用户1的评价历史
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reviewTime").descending());
        Page<ReviewDO> reviews = reviewService.getReviewsByUserId(1L, pageable);
        System.out.println("用户1的评价历史：" + reviews.getContent());
    }

    @Test
    public void testGetReviewStatisticsByProductId() {
        // 查询商品1的评价统计信息
        Map<String, Object> statistics = reviewService.getReviewStatisticsByProductId(1L);
        System.out.println("商品1的评价统计信息：" + statistics);
    }

}