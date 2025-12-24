package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.model.ProductRatingStatDO;
import cn.xilikeli.staging.service.ProductRatingStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品评分统计控制器
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@RestController
@RequestMapping("/api/v1/product-rating-stats")
@Api(tags = "商品评分统计")
@RequiredArgsConstructor
public class ProductRatingStatController {

    private final ProductRatingStatService productRatingStatService;

    @PostMapping
    @ApiOperation("创建商品评分统计")
    public ProductRatingStatDO createProductRatingStat(@RequestBody ProductRatingStatDO productRatingStatDO) {
        return productRatingStatService.createProductRatingStat(productRatingStatDO);
    }

    @GetMapping("/product/{productId}")
    @ApiOperation("根据商品ID查询评分统计")
    public ProductRatingStatDO getProductRatingStatByProductId(@PathVariable Long productId) {
        return productRatingStatService.getProductRatingStatByProductId(productId);
    }

    @PutMapping("/product/{productId}")
    @ApiOperation("更新商品评分统计")
    public ProductRatingStatDO updateProductRatingStat(@PathVariable Long productId, @RequestBody ProductRatingStatDO productRatingStatDO) {
        return productRatingStatService.updateProductRatingStat(productId, productRatingStatDO);
    }

    @DeleteMapping("/product/{productId}")
    @ApiOperation("删除商品评分统计")
    public void deleteProductRatingStat(@PathVariable Long productId) {
        productRatingStatService.deleteProductRatingStat(productId);
    }

}