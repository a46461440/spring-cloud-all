package com.zxc.common.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * {@link java.util.stream.Stream}测试
 *
 * @author Zhou RunMing
 * @Date 2019-1-14
 */
@Slf4j
public class StreamTest {

    @Test
    public void testStream1() {
        Stream.of(1, 2, 3, 4, 8, 56, 7, 8, 9, 0, 13, 123, 151, 23)
                .filter(i -> i % 2 == 0)
                .sorted((s1, s2) -> {
                    return s2.compareTo(s1);
                })
                .distinct()
                .limit(3)
                .map(i -> String.format("%s is odd", i))
                .forEach(i -> this.log.info(i));
    }

}
