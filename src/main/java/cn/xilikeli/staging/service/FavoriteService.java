package cn.xilikeli.staging.service;

import cn.xilikeli.staging.vo.book.BookSampleVO;
import cn.xilikeli.staging.vo.response.PagingDozerVO;

import java.util.List;

/**
 * <p>
 * 用户收藏业务操作接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 */
public interface FavoriteService {

    /**
     * 用户收藏商品
     *
     * @param productId 商品 ID
     * @param productType 商品类型
     */
    void addFavorite(Long productId, String productType);

    /**
     * 用户取消收藏商品
     *
     * @param productId 商品 ID
     */
    void removeFavorite(Long productId);

    /**
     * 用户批量取消收藏商品
     *
     * @param productIds 商品 ID 列表
     */
    void batchRemoveFavorite(List<Long> productIds);

    /**
     * 获取用户收藏列表
     *
     * @param start 起始位置
     * @param count 获取数量
     * @return 返回分页后的用户收藏列表
     */
    PagingDozerVO getFavoriteList(Integer start, Integer count);

    /**
     * 检查用户是否已收藏该商品
     *
     * @param productId 商品 ID
     * @return 返回用户是否已收藏该商品
     */
    boolean checkIsFavorite(Long productId);

}
