package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.model.FavoriteDO;
import cn.xilikeli.staging.repository.FavoriteRepository;
import cn.xilikeli.staging.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户收藏商品业务逻辑实现类
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Slf4j
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public void addFavorite(Long userId, Long itemId, String itemType, String itemName, Long itemPrice, String itemImage) {
        // 检查是否已经收藏
        Optional<FavoriteDO> existingFavorite = favoriteRepository.findByUserIdAndItemId(userId, itemId);
        if (existingFavorite.isPresent()) {
            throw new RuntimeException("该商品已被收藏");
        }

        // 创建新的收藏记录
        FavoriteDO favoriteDO = FavoriteDO.builder()
                .userId(userId)
                .itemId(itemId)
                .itemType(itemType)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemImage(itemImage)
                .build();

        favoriteRepository.save(favoriteDO);
    }

    @Override
    public void removeFavorite(Long userId, Long itemId) {
        Optional<FavoriteDO> favoriteDO = favoriteRepository.findByUserIdAndItemId(userId, itemId);
        if (favoriteDO.isPresent()) {
            favoriteRepository.delete(favoriteDO.get().getId());
        } else {
            throw new RuntimeException("该商品未被收藏");
        }
    }

    @Override
    public void batchRemoveFavorite(Long userId, List<Long> favoriteIdList) {
        List<FavoriteDO> favoriteDOList = favoriteRepository.findByUserIdAndIdIn(userId, favoriteIdList);
        if (favoriteDOList.size() != favoriteIdList.size()) {
            throw new RuntimeException("部分收藏记录不存在");
        }
        favoriteRepository.deleteInBatch(favoriteIdList);
    }

    @Override
    public Page<FavoriteDO> getFavoriteList(Long userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return favoriteRepository.findByUserIdOrderByCreateTimeDesc(userId, pageable);
    }

    @Override
    public boolean isFavorite(Long userId, Long itemId) {
        Optional<FavoriteDO> favoriteDO = favoriteRepository.findByUserIdAndItemId(userId, itemId);
        return favoriteDO.isPresent();
    }

    @Override
    public List<Map<String, Object>> getHotFavoriteItems(int limit) {
        List<Object[]> results = favoriteRepository.findHotFavoriteItems(limit);
        return results.stream()
                .map(result -> Map.of(
                        "itemId", result[0],
                        "count", result[1]
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getFavoritePreference(Long userId) {
        // 查询用户所有收藏记录
        List<FavoriteDO> favorites = favoriteRepository.findByUserId(userId);
        
        // 分析商品类型分布
        Map<String, Long> typeDistribution = favorites.stream()
                .collect(Collectors.groupingBy(FavoriteDO::getItemType, Collectors.counting()));
        
        // 分析价格区间分布
        Map<String, Long> priceRangeDistribution = favorites.stream()
                .collect(Collectors.groupingBy(favorite -> {
                    Long price = favorite.getItemPrice();
                    if (price < 1000) return "0-1000";
                    if (price < 5000) return "1000-5000";
                    if (price < 10000) return "5000-10000";
                    return "10000+";
                }, Collectors.counting()));
        
        // 计算总收藏数量和平均价格
        Long totalFavorites = (long) favorites.size();
        Long totalPrice = favorites.stream()
                .mapToLong(FavoriteDO::getItemPrice)
                .sum();
        Long averagePrice = totalFavorites > 0 ? totalPrice / totalFavorites : 0;
        
        return Map.of(
                "userId", userId,
                "typeDistribution", typeDistribution,
                "priceRangeDistribution", priceRangeDistribution,
                "totalFavorites", totalFavorites,
                "averagePrice", averagePrice
        );
    }

    @Override
    public Map<String, Object> getFavoriteConversionRate(Long itemId) {
        // 查询该商品的收藏次数
        Long favoriteCount = favoriteRepository.countByItemId(itemId);
        
        // TODO: 这里需要从订单表或其他相关表中获取购买次数
        // 暂时用模拟数据，实际项目中需要关联订单表查询
        Long purchaseCount = 0L; // 模拟购买次数
        
        // 计算转化率
        Double conversionRate = favoriteCount > 0 ? (double) purchaseCount / favoriteCount : 0.0;
        
        return Map.of(
                "itemId", itemId,
                "favoriteCount", favoriteCount,
                "purchaseCount", purchaseCount,
                "conversionRate", conversionRate
        );
    }

    @Override
    public void sendPriceDropNotification(Long itemId, Long oldPrice, Long newPrice) {
        // 查询收藏该商品的所有用户
        List<FavoriteDO> favorites = favoriteRepository.findByItemId(itemId);
        
        // 为每个用户发送降价通知
        for (FavoriteDO favorite : favorites) {
            Long userId = favorite.getUserId();
            log.info("向用户 {} 发送商品降价通知: 商品 ID={}, 商品名称={}, 旧价格={}, 新价格={}",
                    userId, itemId, favorite.getItemName(), oldPrice, newPrice);
            
            // TODO: 这里可以集成实际的通知服务（如邮件、短信、推送等）
            // 例如调用邮件服务发送通知
            // emailService.sendPriceDropNotification(userId, itemId, favorite.getItemName(), oldPrice, newPrice);
        }
    }

    @Override
    public void sendStockRestoreNotification(Long itemId) {
        // 查询收藏该商品的所有用户
        List<FavoriteDO> favorites = favoriteRepository.findByItemId(itemId);
        
        // 为每个用户发送库存恢复通知
        for (FavoriteDO favorite : favorites) {
            Long userId = favorite.getUserId();
            log.info("向用户 {} 发送库存恢复通知: 商品 ID={}, 商品名称={}",
                    userId, itemId, favorite.getItemName());
            
            // TODO: 这里可以集成实际的通知服务
        }
    }

    @Override
    public void sendPromotionNotification(Long itemId, String promotionInfo) {
        // 查询收藏该商品的所有用户
        List<FavoriteDO> favorites = favoriteRepository.findByItemId(itemId);
        
        // 为每个用户发送促销活动通知
        for (FavoriteDO favorite : favorites) {
            Long userId = favorite.getUserId();
            log.info("向用户 {} 发送促销活动通知: 商品 ID={}, 商品名称={}, 促销信息={}",
                    userId, itemId, favorite.getItemName(), promotionInfo);
            
            // TODO: 这里可以集成实际的通知服务
        }
    }

}