package com.github.antiportal.dao;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hom.EntityManagerImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;


/**
 * Daneel Yaitskov
 */
public class HectorTest {

    @Test
    public void test() {
        Cluster cluster = HFactory.getOrCreateCluster("AntiPortal", "cas3:9160");
        Keyspace keyspace = HFactory.createKeyspace("xxx", cluster);

        try {
            EntityManagerImpl em = new EntityManagerImpl(keyspace, "com.github.antiportal.dao");

            MyPojo pojo1 = new MyPojo();
            pojo1.setId(33);
            pojo1.setName("hello");
            em.persist(pojo1);

            MyPojo pojo2 = em.find(MyPojo.class, pojo1.getId());

            Assert.assertNull(pojo2);

            em.persist(pojo1);

            pojo2 = em.find(MyPojo.class, pojo1.getId());

            Assert.assertEquals(pojo1, pojo2);
        } finally {
            cluster.getConnectionManager().shutdown();
        }
    }
}
