package com.zxc.eureka.data;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Zhou RunMing
 * @Date 2019-1-25
 */
@Document(collection = "testCollection")
public class MongoEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MongoEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
