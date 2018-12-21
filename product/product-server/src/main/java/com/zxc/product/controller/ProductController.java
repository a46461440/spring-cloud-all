package com.zxc.product.controller;

import com.zxc.product.domain.ProductStockInfo;
import com.zxc.product.domain.po.ProductCategory;
import com.zxc.product.domain.po.ProductInfo;
import com.zxc.product.domain.vo.ProductInfoVO;
import com.zxc.product.domain.vo.ProductVO;
import com.zxc.product.domain.vo.ResultVO;
import com.zxc.product.service.CategoryService;
import com.zxc.product.service.ProductService;
import com.zxc.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品 {@link RestController}实现
 *
 * @author Zhou RunMing
 * @Date 2018-12-13
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所在架的商品
     * 获取类目type列表
     * 查询类目
     * 构造数据
     */
    @GetMapping("/list")
    @CrossOrigin(allowCredentials = "true")//允许cookie跨域
    public ResultVO<List<ProductVO>> getList() {
        List<ProductInfo> productInfoList = this.productService.findUpAll();
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = this.categoryService.findByCategoryTypeIn(categoryTypeList);
        return ResultVOUtil.success(convertProductInfoVO2ProductVO(productInfoList, productCategoryList));
    }

    /**
     * 将ProductInfo表与ProductCategory表查出来的数据组装成{@link ResultVO}返回VO
     * @param productInfoList 需要组装的Product数据
     * @param productCategoryList ProductInfo里包含的ProductCategory
     * @return
     */
    private List<ProductVO> convertProductInfoVO2ProductVO(List<ProductInfo> productInfoList, List<ProductCategory> productCategoryList) {
        List<ProductVO> productVOList = new ArrayList<>();
        productCategoryList.forEach(productCategory -> {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            productInfoList.forEach(productInfo -> {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            });
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        });
        return productVOList;
    }

    /**
     * 获取商品列表通过商品id
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> getProductListForOrder(@RequestBody List<String> productIdList) {
        List<ProductInfo> list = this.productService.findByProductIdIn(productIdList);
        return list;
    }

    /**
     * 扣减库存
     * @param productList
     */
    @PostMapping("/stock/decrease")
    public Integer decreaseStock(@RequestBody List<ProductStockInfo> productList) {
        return this.productService.decreaseStock(productList);
    }

}
