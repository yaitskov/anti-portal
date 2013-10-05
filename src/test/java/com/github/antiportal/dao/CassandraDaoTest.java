package com.github.antiportal.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.github.antiportal.utils.LogUtil;
import me.prettyprint.hector.testutils.EmbeddedServerHelper;
import org.apache.cassandra.cli.CliMain;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * Daneel S. Yaitskov
 */
public class CassandraDaoTest {

    private static final Logger logger = LogUtil.get();

    EmbeddedServerHelper server;
    Cluster cluster;
    Session session;

    @Before
    public void setUp()
            throws IOException, TTransportException,
            ConfigurationException, InterruptedException
    {
        server = new EmbeddedServerHelper("/cassandra.yaml");
        server.setup();

        // getClass().getResource("/MainConfig.xml").getFile()
        String schemaPath = getClass().getResource("/schema.cql")
                .getFile();
        logger.debug("schema path {}", schemaPath);
        CliMain.main(new String[] {"-host", "localhost",
                "-port", "9161",
                "-f",  schemaPath//,
                //"-username", "myUser",
                //"-password", "123456"
        });

        cluster = Cluster.builder()
                .addContactPoint("localhost:9161").build();

        session = cluster.connect("anti_portal");
    }

    @Test
    public void doit() {
        Insert q = QueryBuilder.insertInto("ticks")
                .value("hour", 10)
                .value("quota", "hello")
                .value("minute", 20)
                .value("price", 1.345);
        session.execute(q);
        logger.debug("inserted");
    }


    @After
    public void tireDown() {
        server.teardown();
    }
}
