package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.model.ProductRatingStatDO;
import cn.xilikeli.staging.repository.ProductRatingStatRepository;
import cn.xilikeli.staging.service.ProductRatingStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评分统计 Service 实现类
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Service
@RequiredArgsConstructor
public class ProductRatingStatServiceImpl implements ProductRatingStatService {

    private final ProductRatingStatRepository productRatingStatRepository;

    @Override
    public ProductRatingStatDO createProductRatingStat(ProductRatingStatDO productRatingStatDO) {
        return productRatingStatRepository.save(productRatingStatDO);
    }

    @Override
    public ProductRatingStatDO getProductRatingStatByProductId(Long productId) {
        return productRatingStatRepository.findByProductId(productId);
    }

    @Override
    public ProductRatingStatDO updateProductRatingStat(Long productId, ProductRatingStatDO productRatingStatDO) {
        ProductRatingStatDO existingStat = productRatingStatRepository.findByProductId(productId);
        if (existingStat != null) {
            existingStat.setTotalReviews(productRatingStatDO.getTotalReviews());
            existingStat.setTotalRating(productRatingStatDO.getTotalRating());
            existingStat.setAverageRating(productRatingStatDO.getAverageRating());
            existingStat.setPositiveRate(productRatingStatDO.getPositiveRate());
            return productRatingStatRepository.save(existingStat);
        }
        return null;
    }

    @Override
    public void deleteProductRatingStat(Long productId) {
        ProductRatingStatDO stat = productRatingStatRepository.findByProductId(productId);
        if (stat != null) {
            productRatingStatRepository.delete(stat);
        }
    }

}