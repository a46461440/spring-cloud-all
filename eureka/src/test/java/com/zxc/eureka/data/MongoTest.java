package com.zxc.eureka.data;

import com.zxc.eureka.EurekaApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * mongo 测试
 *
 * @author Zhou RunMing
 * @Date 2019-1-25
 */
@Component
public class MongoTest extends EurekaApplicationTests{

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void testSave() {
        MongoEntity mongoEntity = new MongoEntity();
        mongoEntity.setName("zxc");
        this.mongoOperations.save(mongoEntity);
    }
    @Test
    public void testQuery() {
        Query query = new Query(Criteria.where("name").is("zxc"));
        MongoEntity mongoEntity = this.mongoOperations.findOne(query, MongoEntity.class);
        System.out.println(mongoEntity.toString());
    }

}
