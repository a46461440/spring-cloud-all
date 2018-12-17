package com.zxc.order.utils.converter.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxc.order.menus.OrderBindingResultEnum;
import com.zxc.order.domain.dto.OrderDTO;
import com.zxc.order.domain.dto.OrderForm;
import com.zxc.order.domain.po.OrderDetail;
import com.zxc.order.exception.OrderException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据转换
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO result = new OrderDTO();
        result.setBuyerName(orderForm.getName());
        result.setBuyerPhone(orderForm.getPhone());
        result.setBuyerAddress(orderForm.getAddress());
        result.setBuyerOpenid(orderForm.getOpenId());
        List<OrderDetail> orderDetailList = new ArrayList();
        try {
            Gson gson = new Gson();
            //以下两种方式都可以
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
//        gson.fromJson(orderForm.getItems(),orderDetailList.getClass());
        } catch (Exception e) {
            log.error("【json转换】错误,string = {}", orderForm.getItems());
            throw new OrderException(OrderBindingResultEnum.PARAM_ERROR);
        }
        result.setOrderDetailList(orderDetailList);
        return result;
    }

}
