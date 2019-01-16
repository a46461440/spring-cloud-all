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
            "A.create_time," +
            "A.update_time," +
            "D.c_role" +
            ") FROM user_info A " +
            "INNER JOIN user_role_group_relation B ON A.id=B.n_uid " +
            "INNER JOIN role_group C ON B.n_gid=C.n_gid " +
            "INNER JOIN role D ON C.n_rid=D.n_rid " +
            "WHERE A.id=:userId", nativeQuery = true)
    List<UserInfoWithRole> findRoleByUserId(@Param("userId") Integer userId);
}
