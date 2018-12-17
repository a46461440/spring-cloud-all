package com.zxc.order.domain.vo;

import lombok.Data;

/**
 * Http返回最外层参数
 *
 * @author Zhou RunMing
 * @date 2018/12/13
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;

}
