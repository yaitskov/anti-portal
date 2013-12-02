package com.github.antiportal.dao;

import com.github.antiportal.utils.LogUtil;
//import com.netflix.astyanax.AstyanaxContext;
//import com.netflix.astyanax.Keyspace;
//import com.netflix.astyanax.connectionpool.ConnectionPoolConfiguration;
//import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
//import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
//import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
//import com.netflix.astyanax.connectionpool.impl.CountingConnectionPoolMonitor;
//import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
//import com.netflix.astyanax.thrift.ThriftFamilyFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Daneel S. Yaitskov
 */
@Configuration
public class DaoConfiguration {

    private static final Logger logger = LogUtil.get();

    @Value("cassandra.address")
    private String cassandraAddress;

    @Value("cassandra.keyspace")
    private String keyspaceName;

    @Value("cassandra.cluster")
    private String cassandraCluster;

//    private AstyanaxContext<Keyspace> ctx;
//
//    @Bean
//    public AstyanaxContext<Keyspace> astyanaxContext()
//            throws ConnectionException
//    {
//        logger.debug("create astyanax context");
//
//        ConnectionPoolConfiguration pool
//                = new ConnectionPoolConfigurationImpl("MyConnectionPool")
//                .setMaxConnsPerHost(1)
//                .setSeeds(cassandraAddress);
//
//        ctx = new AstyanaxContext.Builder()
//                .forCluster(cassandraCluster)
//                .forKeyspace(keyspaceName)
//                .withAstyanaxConfiguration(
//                        new AstyanaxConfigurationImpl()
//                                .setDiscoveryType(NodeDiscoveryType.NONE))
//                .withConnectionPoolConfiguration(pool)
//                .withConnectionPoolMonitor(new CountingConnectionPoolMonitor())
//                .buildKeyspace(ThriftFamilyFactory.getInstance());
//
//
//        logger.debug("start astyanax context");
//        ctx.start();
//
//        ctx.getEntity().describeKeyspace();
//        logger.debug("cassandra is ready");
//        return ctx;
//    }
//
//    @Bean
//    public Keyspace keyspace(AstyanaxContext<Keyspace> context) {
//        return context.getEntity();
//    }
//
//    @PreDestroy
//    public void clean() {
//        logger.debug("shutting down astyanax");
//        ctx.shutdown();
//    }
}
