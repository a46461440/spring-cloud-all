package com.zxc.product.repository;

import com.zxc.product.domain.po.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Zhou RunMing
 * @Date 2018-12-13
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);

    @Modifying
    @Query("update ProductInfo SET productStock=:originNum - :decreaseNum " +
            "where productId in :productIds and productStock in :originNum")
    void updateProductStock(@Param("originNum") Integer originNum,
                            @Param("decreaseNum") Integer count,
                            @Param("productIds") List<String> productIds);

}
