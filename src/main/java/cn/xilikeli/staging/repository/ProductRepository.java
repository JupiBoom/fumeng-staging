package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.ProductDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品 Repository 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductDO, Long> {

    /**
     * 根据商品分类查询商品列表
     *
     * @param category 商品分类
     * @return 商品列表
     */
    List<ProductDO> findByCategory(String category);

    /**
     * 根据商品名称模糊查询商品列表
     *
     * @param name 商品名称
     * @return 商品列表
     */
    List<ProductDO> findByNameContaining(String name);

    /**
     * 查询上架商品列表
     *
     * @return 上架商品列表
     */
    List<ProductDO> findByStatus(Integer status);

}