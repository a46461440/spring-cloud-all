package com.zxc.order.controller;

import com.zxc.product.domain.ProductInfo;
import com.zxc.product.domain.ProductStockInfo;
import com.zxc.product.feign.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * rest示例
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@RestController
@Slf4j
public class ClientSimpleController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        //第一种方式(url写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForEntity("http://localhost:1101/msg", String.class).getBody();
//        this.log.info("response is {}", result);
        //第二种方式(获取host和port负载均衡)
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s%s", serviceInstance.getHost(), serviceInstance.getPort(), "/msg");
//        String result = restTemplate.getForObject(url, String.class);
        //第三种方式(利用LoadBalanced,可在url里面写入服务名)
//        String result = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        //第四种方式 Feign
        String result = this.productClient.productMsg();
        return result;
    }

    @PostMapping("/listForProductByFeign")
    public List<ProductInfo> getProductInfo() {
        List<ProductInfo> list = this.productClient.getProductListForOrder(Arrays.asList("164103465734242708"));
        return list;
    }

    @PostMapping("/stock/decrease")
    public String decreaseStock() {
        ProductStockInfo productStockInfo = new ProductStockInfo();
        productStockInfo.setProductId("157875196366160023");
        productStockInfo.setProductQuantity(1);
        this.productClient.decreaseStock(Arrays.asList(productStockInfo));
        return "ok";
    }

}
