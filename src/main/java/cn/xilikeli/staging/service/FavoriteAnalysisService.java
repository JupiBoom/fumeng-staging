package cn.xilikeli.staging.service;

import cn.xilikeli.staging.vo.book.BookSampleVO;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏分析服务类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
public interface FavoriteAnalysisService {

    /**
     * 获取热门收藏商品排行
     *
     * @param topN 获取前 N 个热门商品
     * @return 返回热门收藏商品列表
     */
    List<Map<String, Object>> getHotFavoriteProducts(Integer topN);

    /**
     * 获取用户收藏偏好分析
     *
     * @param accountId 用户 ID
     * @return 返回用户收藏偏好分析结果
     */
    Map<String, Object> getUserFavoritePreference(Integer accountId);

    /**
     * 统计收藏转化率（收藏到购买）
     *
     * @param productId 商品 ID
     * @return 返回收藏转化率
     */
    Double getFavoriteConversionRate(Long productId);

    /**
     * 获取总体收藏统计数据
     *
     * @return 返回总体收藏统计数据
     */
    Map<String, Object> getFavoriteStatistics();

}
