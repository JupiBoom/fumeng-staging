package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.model.FavoriteDO;
import cn.xilikeli.staging.model.BookDO;
import cn.xilikeli.staging.repository.FavoriteRepository;
import cn.xilikeli.staging.repository.BookRepository;
import cn.xilikeli.staging.service.FavoriteAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户收藏分析服务类实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
@Service
public class FavoriteAnalysisServiceImpl implements FavoriteAnalysisService {

    private FavoriteRepository favoriteRepository;
    private BookRepository bookRepository;

    @Autowired
    public void setFavoriteRepository(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Map<String, Object>> getHotFavoriteProducts(Integer topN) {
        // 获取所有收藏记录
        List<FavoriteDO> favoriteDOList = this.favoriteRepository.findAll();

        // 统计每个商品的收藏次数
        Map<Long, Integer> productFavoriteCount = new HashMap<>();
        for (FavoriteDO favoriteDO : favoriteDOList) {
            Long productId = favoriteDO.getProductId();
            productFavoriteCount.put(productId, productFavoriteCount.getOrDefault(productId, 0) + 1);
        }

        // 按收藏次数排序
        List<Map.Entry<Long, Integer>> sortedProducts = new ArrayList<>(productFavoriteCount.entrySet());
        sortedProducts.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // 获取前 N 个商品
        List<Map<String, Object>> hotProducts = new ArrayList<>();
        for (int i = 0; i < Math.min(topN, sortedProducts.size()); i++) {
            Map.Entry<Long, Integer> entry = sortedProducts.get(i);
            Long productId = entry.getKey();
            Integer count = entry.getValue();

            BookDO bookDO = this.bookRepository.findById(productId).orElse(null);
            if (bookDO != null) {
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("productId", productId);
                productInfo.put("title", bookDO.getTitle());
                productInfo.put("author", bookDO.getAuthor());
                productInfo.put("image", bookDO.getImage());
                productInfo.put("price", bookDO.getPrice());
                productInfo.put("favoriteCount", count);
                hotProducts.add(productInfo);
            }
        }

        return hotProducts;
    }

    @Override
    public Map<String, Object> getUserFavoritePreference(Integer accountId) {
        // 获取用户的所有收藏记录
        List<FavoriteDO> userFavoriteList = this.favoriteRepository.findAllByAccountId(accountId);

        // 统计用户收藏的商品信息
        Map<String, Object> preference = new HashMap<>();
        preference.put("totalFavorites", userFavoriteList.size());

        // 获取用户收藏的商品列表
        List<Long> productIds = userFavoriteList.stream()
                .map(FavoriteDO::getProductId)
                .collect(Collectors.toList());

        List<BookDO> bookDOList = this.bookRepository.findAllById(productIds);

        // 计算平均价格
        OptionalDouble avgPrice = bookDOList.stream()
                .mapToInt(BookDO::getPrice)
                .average();
        preference.put("averagePrice", avgPrice.orElse(0));

        // 可以在这里添加更多的偏好分析，比如作者偏好、类型偏好等

        return preference;
    }

    @Override
    public Double getFavoriteConversionRate(Long productId) {
        // 收藏转化率 = 购买数量 / 收藏数量
        // 这里需要假设存在购买记录的数据表
        // 由于当前项目没有购买相关的模型和数据，我们返回一个模拟值
        // 在实际项目中，需要根据真实的购买数据计算
        Integer favoriteCount = this.favoriteRepository.countByProductId(productId);
        if (favoriteCount == 0) {
            return 0.0;
        }

        // 模拟购买数量
        Integer purchaseCount = favoriteCount / 5; // 假设转化率为 20%
        return (double) purchaseCount / favoriteCount;
    }

    @Override
    public Map<String, Object> getFavoriteStatistics() {
        Map<String, Object> statistics = new HashMap<>();

        // 总收藏次数
        Long totalFavorites = this.favoriteRepository.count();
        statistics.put("totalFavorites", totalFavorites);

        // 总收藏用户数
        List<FavoriteDO> favoriteDOList = this.favoriteRepository.findAll();
        Set<Integer> uniqueUsers = favoriteDOList.stream()
                .map(FavoriteDO::getAccountId)
                .collect(Collectors.toSet());
        statistics.put("totalUsers", uniqueUsers.size());

        // 总收藏商品数
        Set<Long> uniqueProducts = favoriteDOList.stream()
                .map(FavoriteDO::getProductId)
                .collect(Collectors.toSet());
        statistics.put("totalProducts", uniqueProducts.size());

        // 平均每个用户的收藏数
        if (uniqueUsers.size() > 0) {
            statistics.put("avgFavoritesPerUser", (double) totalFavorites / uniqueUsers.size());
        } else {
            statistics.put("avgFavoritesPerUser", 0.0);
        }

        return statistics;
    }

}
