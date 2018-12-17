package com.zxc.order.utils;

import com.zxc.order.domain.vo.ResultVO;
import com.zxc.order.menus.ResultVOStatus;

/**
 * {@link ResultVO}返回封装工具类
 *
 * @author Zhou RunMing
 * @date 2018/12/13
 */
public class ResultVOUtil {

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setData(data);
        resultVO.setCode(ResultVOStatus.SUCCESS.getCode());
        resultVO.setMsg(ResultVOStatus.SUCCESS.getMsg());
        return resultVO;
    }

}
