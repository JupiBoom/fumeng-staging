package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.interceptor.ScopeLevel;
import cn.xilikeli.staging.model.FavoriteDO;
import cn.xilikeli.staging.service.FavoriteService;
import cn.xilikeli.staging.vo.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏商品 API 接口控制器
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Validated
@RestController
@RequestMapping("/v1/favorite")
@Api(value = "用户收藏商品 API 接口", tags = {"用户收藏商品业务的相关接口"})
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    /**
     * 用户收藏商品
     *
     * @param userId  用户 ID
     * @param itemId  商品 ID
     * @param itemType 商品类型
     * @param itemName 商品名称
     * @param itemPrice 商品价格
     * @param itemImage 商品图片
     * @return 收藏成功返回成功的响应结果
     */
    @ScopeLevel
    @PostMapping("")
    @ApiOperation(value = "收藏商品接口", notes = "用户收藏商品", httpMethod = "POST")
    public CreatedResponseVO<Object> addFavorite(
            @ApiParam(name = "userId", value = "用户 ID", required = true, example = "1")
            @Positive(message = "{user.id.positive}")
            @RequestParam(name = "userId") Long userId,
            @ApiParam(name = "itemId", value = "商品 ID", required = true, example = "1")
            @Positive(message = "{item.id.positive}")
            @RequestParam(name = "itemId") Long itemId,
            @ApiParam(name = "itemType", value = "商品类型", required = true, example = "book")
            @RequestParam(name = "itemType") String itemType,
            @ApiParam(name = "itemName", value = "商品名称", required = true, example = "Java 编程思想")
            @RequestParam(name = "itemName") String itemName,
            @ApiParam(name = "itemPrice", value = "商品价格", required = true, example = "8990")
            @Positive(message = "{item.price.positive}")
            @RequestParam(name = "itemPrice") Long itemPrice,
            @ApiParam(name = "itemImage", value = "商品图片", required = true, example = "https://example.com/image.jpg")
            @RequestParam(name = "itemImage") String itemImage) {
        this.favoriteService.addFavorite(userId, itemId, itemType, itemName, itemPrice, itemImage);
        return new CreatedResponseVO<>("收藏商品成功");
    }

    /**
     * 用户取消收藏商品
     *
     * @param userId 用户 ID
     * @param itemId 商品 ID
     * @return 取消收藏成功返回成功的响应结果
     */
    @ScopeLevel
    @DeleteMapping("/user/{userId}/item/{itemId}")
    @ApiOperation(value = "取消收藏商品接口", notes = "用户取消收藏商品", httpMethod = "DELETE")
    public DeletedResponseVO<Object> removeFavorite(
            @ApiParam(name = "userId", value = "用户 ID", required = true, example = "1")
            @Positive(message = "{user.id.positive}")
            @PathVariable(name = "userId") Long userId,
            @ApiParam(name = "itemId", value = "商品 ID", required = true, example = "1")
            @Positive(message = "{item.id.positive}")
            @PathVariable(name = "itemId") Long itemId) {
        this.favoriteService.removeFavorite(userId, itemId);
        return new DeletedResponseVO<>("取消收藏商品成功");
    }

    /**
     * 用户批量取消收藏商品
     *
     * @param userId 用户 ID
     * @param favoriteIdList 收藏记录 ID 列表
     * @return 批量取消收藏成功返回成功的响应结果
     */
    @ScopeLevel
    @DeleteMapping("/batch")
    @ApiOperation(value = "批量取消收藏商品接口", notes = "用户批量取消收藏商品", httpMethod = "DELETE")
    public DeletedResponseVO<Object> batchRemoveFavorite(
            @ApiParam(name = "userId", value = "用户 ID", required = true, example = "1")
            @Positive(message = "{user.id.positive}")
            @RequestParam(name = "userId") Long userId,
            @ApiParam(name = "favoriteIdList", value = "收藏记录 ID 列表", required = true, example = "[1,2,3]")
            @RequestBody List<Long> favoriteIdList) {
        this.favoriteService.batchRemoveFavorite(userId, favoriteIdList);
        return new DeletedResponseVO<>("批量取消收藏商品成功");
    }

    /**
     * 获取用户收藏列表
     *
     * @param userId 用户 ID
     * @param page  当前页数
     * @param size 每页数量
     * @return 返回用户收藏列表的分页视图对象
     */
    @ScopeLevel
    @GetMapping("/user/{userId}/list")
    @ApiOperation(value = "获取用户收藏列表接口", notes = "获取用户收藏列表", httpMethod = "GET")
    public PagingVO<FavoriteDO> getFavoriteList(
            @ApiParam(name = "userId", value = "用户 ID", required = true, example = "1")
            @Positive(message = "{user.id.positive}")
            @PathVariable(name = "userId") Long userId,
            @Min(value = 1, message = "{page.number.min}")
            @ApiParam(name = "page", value = "当前页数", defaultValue = "1", example = "1")
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}")
            @ApiParam(name = "size", value = "每页数量", defaultValue = "10", example = "10")
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Page<FavoriteDO> favoritePage = this.favoriteService.getFavoriteList(userId, page, size);
        return new PagingVO<>(favoritePage);
    }

    /**
     * 检查用户是否已收藏该商品
     *
     * @param userId 用户 ID
     * @param itemId 商品 ID
     * @return 返回用户是否已收藏该商品的结果
     */
    @ScopeLevel
    @GetMapping("/user/{userId}/item/{itemId}/check")
    @ApiOperation(value = "检查商品是否已收藏接口", notes = "检查用户是否已收藏该商品", httpMethod = "GET")
    public CreatedResponseVO<Boolean> isFavorite(
            @ApiParam(name = "userId", value = "用户 ID", required = true, example = "1")
            @Positive(message = "{user.id.positive}")
            @PathVariable(name = "userId") Long userId,
            @ApiParam(name = "itemId", value = "商品 ID", required = true, example = "1")
            @Positive(message = "{item.id.positive}")
            @PathVariable(name = "itemId") Long itemId) {
        boolean isFavorite = this.favoriteService.isFavorite(userId, itemId);
        return new CreatedResponseVO<>(isFavorite);
    }

    /**
     * 获取热门收藏商品排行
     *
     * @param limit 查询数量
     * @return 返回热门收藏商品排行
     */
    @ScopeLevel
    @GetMapping("/hot")
    @ApiOperation(value = "获取热门收藏商品排行接口", notes = "获取热门收藏商品排行", httpMethod = "GET")
    public CreatedResponseVO<List<Map<String, Object>>> getHotFavoriteItems(
            @Min(value = 1, message = "{limit.min}")
            @Max(value = 50, message = "{limit.max}")
            @ApiParam(name = "limit", value = "查询数量", defaultValue = "10", example = "10")
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        List<Map<String, Object>> hotItems = this.favoriteService.getHotFavoriteItems(limit);
        return new CreatedResponseVO<>(hotItems);
    }

    /**
     * 获取用户收藏偏好分析
     *
     * @param userId 用户 ID
     * @return 返回用户收藏偏好分析结果
     */
    @ScopeLevel
    @GetMapping("/preference/user/{userId}")
    @ApiOperation(value = "获取用户收藏偏好分析接口", notes = "获取用户收藏偏好分析", httpMethod = "GET")
    public CreatedResponseVO<Map<String, Object>> getFavoritePreference(
            @ApiParam(name = "userId", value = "用户 ID", required = true, example = "1")
            @Positive(message = "{user.id.positive}")
            @PathVariable(name = "userId") Long userId) {
        Map<String, Object> preference = this.favoriteService.getFavoritePreference(userId);
        return new CreatedResponseVO<>(preference);
    }

    /**
     * 获取收藏转化率统计
     *
     * @param itemId 商品 ID
     * @return 返回收藏转化率统计结果
     */
    @ScopeLevel
    @GetMapping("/conversion/item/{itemId}")
    @ApiOperation(value = "获取收藏转化率统计接口", notes = "获取收藏转化率统计", httpMethod = "GET")
    public CreatedResponseVO<Map<String, Object>> getFavoriteConversionRate(
            @ApiParam(name = "itemId", value = "商品 ID", required = true, example = "1")
            @Positive(message = "{item.id.positive}")
            @PathVariable(name = "itemId") Long itemId) {
        Map<String, Object> conversionRate = this.favoriteService.getFavoriteConversionRate(itemId);
        return new CreatedResponseVO<>(conversionRate);
    }

}