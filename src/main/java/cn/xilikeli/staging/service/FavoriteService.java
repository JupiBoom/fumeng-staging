package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.FavoriteDO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏商品业务逻辑接口
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
public interface FavoriteService {

    /**
     * 用户收藏商品
     *
     * @param userId 用户 ID
     * @param itemId 商品 ID
     * @param itemType 商品类型
     * @param itemName 商品名称
     * @param itemPrice 商品价格
     * @param itemImage 商品图片
     */
    void addFavorite(Long userId, Long itemId, String itemType, String itemName, Long itemPrice, String itemImage);

    /**
     * 用户取消收藏商品
     *
     * @param userId 用户 ID
     * @param itemId 商品 ID
     */
    void removeFavorite(Long userId, Long itemId);

    /**
     * 用户批量取消收藏商品
     *
     * @param userId 用户 ID
     * @param favoriteIdList 收藏记录 ID 列表
     */
    void batchRemoveFavorite(Long userId, List<Long> favoriteIdList);

    /**
     * 获取用户收藏列表
     *
     * @param userId 用户 ID
     * @param page 页码
     * @param size 每页数量
     * @return 返回分页的收藏商品列表
     */
    Page<FavoriteDO> getFavoriteList(Long userId, Integer page, Integer size);

    /**
     * 检查用户是否已收藏该商品
     *
     * @param userId 用户 ID
     * @param itemId 商品 ID
     * @return 已收藏返回 true, 未收藏返回 false
     */
    boolean isFavorite(Long userId, Long itemId);

    /**
     * 获取热门收藏商品排行
     *
     * @param limit 查询数量
     * @return 返回热门收藏商品列表
     */
    List<Map<String, Object>> getHotFavoriteItems(int limit);

    /**
     * 获取用户收藏偏好分析
     *
     * @param userId 用户 ID
     * @return 返回用户收藏偏好分析结果
     */
    Map<String, Object> getFavoritePreference(Long userId);

    /**
     * 获取收藏转化率统计
     *
     * @param itemId 商品 ID
     * @return 返回收藏转化率统计结果
     */
    Map<String, Object> getFavoriteConversionRate(Long itemId);

    /**
     * 发送收藏商品降价通知
     *
     * @param itemId 商品 ID
     * @param oldPrice 旧价格
     * @param newPrice 新价格
     */
    void sendPriceDropNotification(Long itemId, Long oldPrice, Long newPrice);

    /**
     * 发送收藏商品库存恢复通知
     *
     * @param itemId 商品 ID
     */
    void sendStockRestoreNotification(Long itemId);

    /**
     * 发送收藏商品促销活动提醒
     *
     * @param itemId 商品 ID
     * @param promotionInfo 促销信息
     */
    void sendPromotionNotification(Long itemId, String promotionInfo);

}