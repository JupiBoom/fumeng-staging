package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.model.ProductDO;
import cn.xilikeli.staging.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品控制器
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@RestController
@RequestMapping("/api/v1/products")
@Api(tags = "商品管理")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ApiOperation("创建商品")
    public ProductDO createProduct(@RequestBody ProductDO productDO) {
        return productService.createProduct(productDO);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询商品")
    public ProductDO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    @ApiOperation("查询商品列表")
    public Page<ProductDO> getProductList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getProductList(pageable);
    }

    @GetMapping("/category/{category}")
    @ApiOperation("根据分类查询商品列表")
    public List<ProductDO> getProductListByCategory(@PathVariable String category) {
        return productService.getProductListByCategory(category);
    }

    @GetMapping("/name/{name}")
    @ApiOperation("根据名称查询商品列表")
    public List<ProductDO> getProductListByName(@PathVariable String name) {
        return productService.getProductListByName(name);
    }

    @GetMapping("/on-sale")
    @ApiOperation("查询上架商品列表")
    public List<ProductDO> getOnSaleProductList() {
        return productService.getOnSaleProductList();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新商品信息")
    public ProductDO updateProduct(@PathVariable Long id, @RequestBody ProductDO productDO) {
        return productService.updateProduct(id, productDO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除商品")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}