package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.ProductDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 商品 Service 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
public interface ProductService {

    /**
     * 创建商品
     *
     * @param productDO 商品信息
     * @return 商品信息
     */
    ProductDO createProduct(ProductDO productDO);

    /**
     * 根据 ID 查询商品
     *
     * @param productId 商品 ID
     * @return 商品信息
     */
    ProductDO getProductById(Long productId);

    /**
     * 查询商品列表（分页）
     *
     * @param pageable 分页参数
     * @return 商品列表
     */
    Page<ProductDO> getProductList(Pageable pageable);

    /**
     * 根据分类查询商品列表
     *
     * @param category 商品分类
     * @return 商品列表
     */
    List<ProductDO> getProductListByCategory(String category);

    /**
     * 根据名称模糊查询商品列表
     *
     * @param name 商品名称
     * @return 商品列表
     */
    List<ProductDO> getProductListByName(String name);

    /**
     * 查询上架商品列表
     *
     * @return 上架商品列表
     */
    List<ProductDO> getOnSaleProductList();

    /**
     * 更新商品信息
     *
     * @param productId 商品 ID
     * @param productDO 商品信息
     * @return 商品信息
     */
    ProductDO updateProduct(Long productId, ProductDO productDO);

    /**
     * 删除商品
     *
     * @param productId 商品 ID
     */
    void deleteProduct(Long productId);

}