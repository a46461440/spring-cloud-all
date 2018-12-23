package com.zxc.apigateway.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Before;
import org.junit.Test;

/**
 * 布隆过滤器
 *
 * @author Zhou RunMing
 * @date 2018/12/23
 */
public class BloomFilterTest {

    private BloomFilter bloomFilter;

    @Before
    public void init() {
        //@param String类型的Bloom过滤器
        //@param 里面可以存多少个数
        //@param 布隆过滤器的错误率 越小则占用内存越多
        this.bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 1000000, 0.003);
    }

    @Test
    public void testBloom() {
        bloomFilter.put("zxc");
        bloomFilter.put("zxm");
        System.out.println(bloomFilter.mightContain("zxc"));
        System.out.println(bloomFilter.mightContain("zxj"));
    }

}
