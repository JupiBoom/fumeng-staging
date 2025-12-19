package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.FavoriteDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户收藏商品 Repository 接口
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
public interface FavoriteRepository extends BaseRepository<FavoriteDO, Long> {

    /**
     * 根据用户 ID 和商品 ID 查询收藏记录
     *
     * @param userId 用户 ID
     * @param itemId 商品 ID
     * @return 返回相应的收藏记录, 查询不到则返回 Optional.empty()
     */
    Optional<FavoriteDO> findByUserIdAndItemId(Long userId, Long itemId);

    /**
     * 根据用户 ID 分页查询收藏记录
     *
     * @param userId   用户 ID
     * @param pageable 分页参数
     * @return 返回分页的收藏记录
     */
    Page<FavoriteDO> findByUserIdOrderByCreateTimeDesc(Long userId, Pageable pageable);

    /**
     * 根据用户 ID 查询所有收藏记录
     *
     * @param userId 用户 ID
     * @return 收藏记录列表
     */
    List<FavoriteDO> findByUserId(Long userId);

    /**
     * 根据商品 ID 查询所有收藏记录
     *
     * @param itemId 商品 ID
     * @return 收藏记录列表
     */
    List<FavoriteDO> findByItemId(Long itemId);

    /**
     * 根据用户 ID 和收藏记录 ID 列表查询收藏记录
     *
     * @param userId     用户 ID
     * @param favoriteIdList 收藏记录 ID 列表
     * @return 返回收藏记录列表
     */
    List<FavoriteDO> findByUserIdAndIdIn(Long userId, List<Long> favoriteIdList);

    /**
     * 查询热门收藏商品排行
     *
     * @param limit 查询数量
     * @return 返回热门收藏商品列表
     */
    @Query(value = "SELECT item_id, COUNT(*) AS count FROM favorite WHERE delete_time IS NULL GROUP BY item_id ORDER BY count DESC LIMIT ?1", nativeQuery = true)
    List<Object[]> findHotFavoriteItems(int limit);

    /**
     * 查询用户收藏的商品数量
     *
     * @param userId 用户 ID
     * @return 返回用户收藏的商品数量
     */
    long countByUserId(Long userId);

    /**
     * 统计商品被收藏的次数
     *
     * @param itemId 商品 ID
     * @return 收藏次数
     */
    long countByItemId(Long itemId);

}