package com.zxc.order.order.controller;

import com.zxc.order.order.domain.dto.OrderDTO;
import com.zxc.order.order.domain.dto.OrderForm;
import com.zxc.order.order.domain.vo.ResultVO;
import com.zxc.order.order.exception.OrderException;
import com.zxc.order.order.menus.OrderBindingResultEnum;
import com.zxc.order.order.service.OrderService;
import com.zxc.order.order.utils.ResultVOUtil;
import com.zxc.order.order.utils.converter.domain.OrderForm2OrderDTOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查询商品信息
     * 3.计算总价
     * 4.记单入库
     */
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResultResult) {
        if (bindingResultResult.hasErrors()) {
            this.log.error("【创建订单】参数不正确, orderForm={}", orderForm.toString());
            throw new OrderException(OrderBindingResultEnum.PARAM_ERROR.getCode(),
                    bindingResultResult.getFieldError().getDefaultMessage().toString());
        }
        //orderFomr -> orderDTO
        OrderDTO orderDTOCarry = OrderForm2OrderDTOConverter.convert(orderForm);
        if (orderDTOCarry != null && CollectionUtils.isEmpty(orderDTOCarry.getOrderDetailList())) {
            this.log.error("【创建订单】购物车信息为空");
            throw new OrderException(OrderBindingResultEnum.CAR_EMPTY);
        }
        OrderDTO result = this.orderService.create(orderDTOCarry);
        ResultVO resultVO = ResultVOUtil.success(result.getOrderId());
        return resultVO;
    }

}
