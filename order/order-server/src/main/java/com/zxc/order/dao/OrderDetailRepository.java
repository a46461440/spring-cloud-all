package com.zxc.order.dao;

import com.zxc.order.domain.po.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{
}
