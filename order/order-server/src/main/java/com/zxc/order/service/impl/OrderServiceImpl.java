package com.zxc.order.service.impl;

import com.zxc.order.domain.po.OrderMaster;
import com.zxc.order.menus.OrderStatus;
import com.zxc.order.dao.OrderDetailRepository;
import com.zxc.order.dao.OrderMasterRespository;
import com.zxc.order.domain.dto.OrderDTO;
import com.zxc.order.domain.po.OrderDetail;
import com.zxc.order.menus.PayStatus;
import com.zxc.order.service.OrderService;
import com.zxc.order.utils.KeyUtil;
import com.zxc.product.domain.ProductInfo;
import com.zxc.product.domain.ProductStockInfo;
import com.zxc.product.feign.client.ProductClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRespository orderMasterRespository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //生成orderId
        String orderId = KeyUtil.genOrdrUniqueKey();
        //查询商品信息(调用商品服务)
        List<String> ids = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.getProductListForOrder(ids);
        //计算总价
        BigDecimal amountMoney = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    amountMoney = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(amountMoney);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setDetailId(KeyUtil.genOrdrUniqueKey());
                    orderDetail.setOrderId(orderId);
                    //订单详情入库
                    this.orderDetailRepository.save(orderDetail);
                }
            }
        }
        //扣库存
        List<ProductStockInfo> productStockInfoListNeedToDecreaseStock = orderDTO.getOrderDetailList().stream()
                .map(e -> {
                    ProductStockInfo productStockInfo = new ProductStockInfo();
                    BeanUtils.copyProperties(e, productStockInfo);
                    return productStockInfo;
                })
                .collect(Collectors.toList());
        this.productClient.decreaseStock(productStockInfoListNeedToDecreaseStock);
        //订单入库
        OrderMaster orderMasterCarry = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMasterCarry);
        setOrderMasterInCreateStatus(orderMasterCarry);
        orderMasterCarry.setOrderAmount(amountMoney);
        this.orderMasterRespository.save(orderMasterCarry);
        return orderDTO;
    }

    /**
     * 将{@link OrderMaster}实体设置为初始状态
     *
     * @param orderMaster
     * @return
     */
    public static OrderMaster setOrderMasterInCreateStatus(OrderMaster orderMaster) {
        if (orderMaster == null)
            return null;
        Date now = new Date();
        orderMaster.setCreateTime(now);
        orderMaster.setUpdateTime(now);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        return orderMaster;
    }
}
