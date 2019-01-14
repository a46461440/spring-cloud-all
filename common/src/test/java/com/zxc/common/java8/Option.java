package com.zxc.common.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

/**
 * {@link java.util.Optional}测试
 *
 * @author Zhou RunMing
 * @Date 2019-1-14
 */
@Slf4j
public class Option {

    @Test
    public void test1() {
        lambda.Customer customer = new lambda.Customer("zxc", 24, true);
        Optional<lambda.Customer> customerOptional = Optional.ofNullable(customer);
        if (customerOptional.isPresent())
            this.log.info(customerOptional.get().toString());
    }

    @Test
    public void test2() {
        lambda.Customer customer = new lambda.Customer("zxc", 24, true);
        Optional<lambda.Customer> customerOptional = Optional.ofNullable(customer);
        customerOptional.ifPresent(c -> this.log.info(c.toString()));
    }

    @Test
    public void test3() {
        Optional<lambda.Customer> customerOptional = Optional.ofNullable(null);
        this.log.info(customerOptional.orElseGet(lambda.Customer::new).toString());
    }

    @Test
    public void test4() {
        lambda.Customer customer = new lambda.Customer("zxc", 24, true);
        Optional<lambda.Customer> customerOptional = Optional.ofNullable(null);
        this.log.info(customerOptional.orElse(customer).toString());
    }

}
