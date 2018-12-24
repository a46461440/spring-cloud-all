package com.zxc.user.repository;

import com.zxc.user.UserTest;
import com.zxc.user.domain.po.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

@Component
@Slf4j
public class UserInfoRepositoryTest extends UserTest{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void testFindByUserNameAndPassword() {
        String name = "ZXC";
        String password = "ZXC";
        UserInfo userInfo = this.userInfoRepository.findByUsernameAndPassword(name, password);
        this.log.info(userInfo.toString());
    }

}