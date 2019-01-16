package com.zxc.user.repository;

import com.zxc.user.domain.po.UserInfo;
import com.zxc.user.domain.po.UserInfoWithRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);

    UserInfo findByUsernameAndPassword(String username, String password);

    List<UserInfo> findByUsername(String username);

    @Query(value = "select new com.zxc.user.domain.po.UserInfoWithRole(" +
            "A.id," +
            "A.username," +
            "A.password," +
            "A.openid," +
            "A.role," +
            "A.createTime," +
            "A.updateTime," +
            "D.cRole" +
            ") FROM UserInfo A " +
            "INNER JOIN com.zxc.user.domain.po.UserRoleGroupRelation B ON A.id=B.nUid " +
            "INNER JOIN com.zxc.user.domain.po.RoleGroup C ON B.nGid=C.nGid " +
            "INNER JOIN com.zxc.user.domain.po.Role D ON C.nRid=D.nRid " +
            "WHERE A.id=:userId")
    List<UserInfoWithRole> findRoleByUserId(@Param("userId") String userId);
}
