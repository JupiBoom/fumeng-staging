package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.interceptor.ScopeLevel;
import cn.xilikeli.staging.service.FavoriteService;
import cn.xilikeli.staging.vo.response.CreatedResponseVO;
import cn.xilikeli.staging.vo.response.DeletedResponseVO;
import cn.xilikeli.staging.vo.response.PagingDozerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户收藏管理接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 */
@RestController
@RequestMapping("v1/favorite")
@Api(tags = "用户收藏管理")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    /**
     * 用户收藏商品
     *
     * @param productId 商品 ID
     * @param productType 商品类型
     * @return 返回创建成功的响应
     */
    @PostMapping("/add")
    @ScopeLevel()
    @ApiOperation(value = "用户收藏商品", notes = "用户收藏商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "商品 ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "productType", value = "商品类型", required = true, dataType = "String", paramType = "query")
    })
    public CreatedResponseVO addFavorite(@RequestParam Long productId, @RequestParam String productType) {
        favoriteService.addFavorite(productId, productType);
        return new CreatedResponseVO();
    }

    /**
     * 用户取消收藏商品
     *
     * @param productId 商品 ID
     * @return 返回删除成功的响应
     */
    @DeleteMapping("/remove/{productId}")
    @ScopeLevel()
    @ApiOperation(value = "用户取消收藏商品", notes = "用户取消收藏商品")
    @ApiImplicitParam(name = "productId", value = "商品 ID", required = true, dataType = "Long", paramType = "path")
    public DeletedResponseVO removeFavorite(@PathVariable Long productId) {
        favoriteService.removeFavorite(productId);
        return new DeletedResponseVO();
    }

    /**
     * 用户批量取消收藏商品
     *
     * @param productIds 商品 ID 列表
     * @return 返回删除成功的响应
     */
    @DeleteMapping("/batch-remove")
    @ScopeLevel()
    @ApiOperation(value = "用户批量取消收藏商品", notes = "用户批量取消收藏商品")
    @ApiImplicitParam(name = "productIds", value = "商品 ID 列表", required = true, dataType = "List", paramType = "body")
    public DeletedResponseVO batchRemoveFavorite(@RequestBody List<Long> productIds) {
        favoriteService.batchRemoveFavorite(productIds);
        return new DeletedResponseVO();
    }

    /**
     * 获取用户收藏列表
     *
     * @param start 起始位置
     * @param count 获取数量
     * @return 返回用户收藏列表
     */
    @GetMapping("/list")
    @ScopeLevel()
    @ApiOperation(value = "获取用户收藏列表", notes = "获取用户收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "起始位置", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "count", value = "获取数量", required = true, dataType = "Integer", paramType = "query")
    })
    public PagingDozerVO getFavoriteList(@RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "10") Integer count) {
        return favoriteService.getFavoriteList(start, count);
    }

    /**
     * 检查用户是否已收藏该商品
     *
     * @param productId 商品 ID
     * @return 返回用户是否已收藏该商品
     */
    @GetMapping("/check/{productId}")
    @ScopeLevel()
    @ApiOperation(value = "检查用户是否已收藏该商品", notes = "检查用户是否已收藏该商品")
    @ApiImplicitParam(name = "productId", value = "商品 ID", required = true, dataType = "Long", paramType = "path")
    public Boolean checkIsFavorite(@PathVariable Long productId) {
        return favoriteService.checkIsFavorite(productId);
    }

}
