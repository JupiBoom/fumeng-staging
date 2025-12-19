package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.FavoriteDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户收藏 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 */
@Repository
public interface FavoriteRepository extends BaseRepository<FavoriteDO, Integer> {

    /**
     * 根据用户 ID 和商品 ID 查询收藏记录
     *
     * @param accountId  用户 ID
     * @param productId 商品 ID
     * @return 返回查询到的收藏记录
     */
    Optional<FavoriteDO> findByAccountIdAndProductId(Integer accountId, Long productId);

    /**
     * 根据用户 ID 和商品 ID 列表查询收藏记录
     *
     * @param accountId  用户 ID
     * @param productIds 商品 ID 列表
     * @return 返回查询到的收藏记录列表
     */
    List<FavoriteDO> findByAccountIdAndProductIdIn(Integer accountId, List<Long> productIds);

    /**
     * 根据商品 ID 查询收藏记录
     *
     * @param productId 商品 ID
     * @return 返回查询到的收藏记录列表
     */
    List<FavoriteDO> findByProductId(Long productId);

    /**
     * 根据用户 ID 查询收藏记录数量
     *
     * @param accountId 用户 ID
     * @return 返回收藏记录数量
     */
    Integer countByAccountId(Integer accountId);

    /**
     * 统计商品被收藏的次数
     *
     * @param productId 商品 ID
     * @return 返回商品被收藏的次数
     */
    Integer countByProductId(Long productId);

    /**
     * 根据用户 ID 查询所有收藏记录
     *
     * @param accountId 用户 ID
     * @return 返回查询到的收藏记录列表
     */
    List<FavoriteDO> findAllByAccountId(Integer accountId);

    /**
     * 根据用户 ID 分页查询收藏记录
     *
     * @param accountId 用户 ID
     * @param pageable  分页参数
     * @return 返回查询到的收藏记录分页对象
     */
    Page<FavoriteDO> findAllByAccountId(Integer accountId, Pageable pageable);

    /**
     * 根据用户 ID 和商品 ID 删除收藏记录
     *
     * @param accountId  用户 ID
     * @param productId 商品 ID
     * @return 返回删除的记录数
     */
    @Modifying
    @Query("DELETE FROM FavoriteDO f WHERE f.accountId = ?1 AND f.productId = ?2")
    Integer deleteByAccountIdAndProductId(Integer accountId, Long productId);

    /**
     * 根据用户 ID 和商品 ID 列表批量删除收藏记录
     *
     * @param accountId  用户 ID
     * @param productIds 商品 ID 列表
     * @return 返回删除的记录数
     */
    @Modifying
    @Query("DELETE FROM FavoriteDO f WHERE f.accountId = ?1 AND f.productId IN (?2)")
    Integer deleteByAccountIdAndProductIdIn(Integer accountId, List<Long> productIds);

}
