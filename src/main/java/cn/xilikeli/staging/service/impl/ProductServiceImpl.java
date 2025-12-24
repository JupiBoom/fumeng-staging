package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.model.ProductDO;
import cn.xilikeli.staging.repository.ProductRepository;
import cn.xilikeli.staging.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品 Service 实现类
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDO createProduct(ProductDO productDO) {
        return productRepository.save(productDO);
    }

    @Override
    public ProductDO getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Page<ProductDO> getProductList(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<ProductDO> getProductListByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<ProductDO> getProductListByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public List<ProductDO> getOnSaleProductList() {
        return productRepository.findByStatus(1);
    }

    @Override
    public ProductDO updateProduct(Long productId, ProductDO productDO) {
        ProductDO existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(productDO.getName());
            existingProduct.setDescription(productDO.getDescription());
            existingProduct.setPrice(productDO.getPrice());
            existingProduct.setStock(productDO.getStock());
            existingProduct.setImage(productDO.getImage());
            existingProduct.setCategory(productDO.getCategory());
            existingProduct.setStatus(productDO.getStatus());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}