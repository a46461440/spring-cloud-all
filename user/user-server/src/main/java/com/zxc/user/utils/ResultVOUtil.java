package com.zxc.user.utils;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */

import com.zxc.product.enums.ResultVOStatus;
import com.zxc.user.domain.vo.ResultVO;

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
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(ResultVOStatus.SUCCESS.getCode());
        resultVO.setMsg(ResultVOStatus.SUCCESS.getMsg());
        return resultVO;
    }

    public static ResultVO error(ResultVOStatus resultVOStatus) {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(resultVOStatus.getCode());
        resultVO.setMsg(resultVOStatus.getMsg());
        return resultVO;
    }

}
