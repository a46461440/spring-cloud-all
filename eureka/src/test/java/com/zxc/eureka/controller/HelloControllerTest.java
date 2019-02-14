package com.zxc.eureka.controller;

import com.zxc.eureka.EurekaApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Zhou RunMing
 * @Date 2019-2-14
 */
@DataJpaTest
public class HelloControllerTest extends EurekaApplicationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    /**
     * 如果返回值有对象，可以通过如下来判定
     * .andExpect(jsonPath("$.name").value("zxc"))
     * @throws Exception
     */
    @Test
    public void testHelloController() throws Exception {
        ResultActions resultActions = this.mockMvc
                .perform(get("/hello").param("id", "w"))
                .andExpect(status().isOk());
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testJpa() {
//        this.entityManager.getEntityManager().createQuery();
    }

}
