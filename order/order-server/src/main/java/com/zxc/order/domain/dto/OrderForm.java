package com.zxc.order.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单入库表单
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "电话必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "微信号必填")
    private String openId;

    @NotEmpty(message = "购物车不能为空")
    private String items;

}
