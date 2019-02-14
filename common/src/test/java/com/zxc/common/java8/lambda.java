package com.zxc.common.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 测试lambda
 *
 * @author Zhou RunMing
 * @date 2019/1/13
 */
@Slf4j
public class lambda {

    public void test1(List<Integer> list, Predicate<Integer> predicate) {
        list.stream().forEach(l -> {
            if (predicate.test(l))
                this.log.info("{}是大于5的", l);
        });
    }

    @Test
    public void testPredicate() {
        List<Integer> list = Arrays.asList(1, 5, 6, 7, 8, 9, 10, 4, 2);
        this.test1(list, l -> l > 5);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {
        private String name;
        private Integer age;
        private boolean sex;

        public String print(Function<Customer, String> function) {
            return function.apply(this);
        }
    }

    @Test
    public void testFunction() {
        Customer customer = new Customer("zxc", 24, true);
        String msg = customer.print(c -> String.format("name is %s,the age is %d and the sex is %s",
                customer.getName(), customer.getAge(), customer.isSex() ? "male" : "female"));
        this.log.info(msg);
    }

    public Customer supplierProvide(Supplier<Customer> supplier) {
        return supplier.get();
    }

    @Test
    public void testApplier() {
        System.out.println(this.supplierProvide(() -> new Customer()) == this.supplierProvide(Customer::new));
    }

    @Test
    public void testConsumer() {
        Consumer<Customer> consumer = c -> this.log.info(c.toString());
        Consumer<Customer> consumer2 = c -> this.log.info(c.toString() + "ok");
        Customer customer = new Customer();
        consumer.andThen(consumer2).accept(customer);
    }

    public void testFunctionInterface(Function<String, String> function, String s) {
        log.info(function.andThen(so -> so + "s").apply(s));
    }

    @Test
    public void testFunctionInterface() {
        this.testFunctionInterface((s) -> s + "o", "s");
    }
}