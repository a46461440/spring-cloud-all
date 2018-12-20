package com.zxc.order.dao;

import com.zxc.order.domain.po.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    @Modifying
    @Query(value = "update OrderMaster SET orderStatus=:newStatus where orderId=:orderId and orderStatus=:oldStatus")
    Integer updateChangeStatus(@Param("orderId") String orderId,
                               @Param("oldStatus") Integer oldStatus,
                               @Param("newStatus") Integer newStatus);

}
