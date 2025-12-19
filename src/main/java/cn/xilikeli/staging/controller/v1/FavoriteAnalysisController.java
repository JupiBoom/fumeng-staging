package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.interceptor.ScopeLevel;
import cn.xilikeli.staging.service.FavoriteAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏分析接口控制器
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
@RestController
@RequestMapping("v1/favorite/analysis")
@Api(tags = "用户收藏分析接口")
public class FavoriteAnalysisController {

    private FavoriteAnalysisService favoriteAnalysisService;

    @Autowired
    public void setFavoriteAnalysisService(FavoriteAnalysisService favoriteAnalysisService) {
        this.favoriteAnalysisService = favoriteAnalysisService;
    }

    @GetMapping("hot-products")
    @ScopeLevel()
    @ApiOperation(value = "获取热门收藏商品排行", notes = "获取热门收藏商品排行")
    public List<Map<String, Object>> getHotFavoriteProducts(
            @ApiParam(value = "获取前 N 个热门商品", required = true, defaultValue = "10") @RequestParam(value = "top_n", defaultValue = "10") Integer topN) {
        return this.favoriteAnalysisService.getHotFavoriteProducts(topN);
    }

    @GetMapping("user-preference")
    @ScopeLevel()
    @ApiOperation(value = "获取用户收藏偏好分析", notes = "获取用户收藏偏好分析")
    public Map<String, Object> getUserFavoritePreference() {
        Integer accountId = cn.xilikeli.staging.common.LocalUser.getAccountId();
        return this.favoriteAnalysisService.getUserFavoritePreference(accountId);
    }

    @GetMapping("conversion-rate/{product_id}")
    @ScopeLevel()
    @ApiOperation(value = "统计收藏转化率", notes = "统计收藏转化率（收藏到购买）")
    public Double getFavoriteConversionRate(
            @ApiParam(value = "商品 ID", required = true) @PathVariable("product_id") Long productId) {
        return this.favoriteAnalysisService.getFavoriteConversionRate(productId);
    }

    @GetMapping("statistics")
    @ScopeLevel()
    @ApiOperation(value = "获取总体收藏统计数据", notes = "获取总体收藏统计数据")
    public Map<String, Object> getFavoriteStatistics() {
        return this.favoriteAnalysisService.getFavoriteStatistics();
    }

}
