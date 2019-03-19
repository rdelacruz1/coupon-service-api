package com.rdelacruz.couponserviceapi;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;

public class EmbeddedCassandraInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private final boolean addShutdownHook;

    public EmbeddedCassandraInitializer(boolean addShutdownHook) {
        this.addShutdownHook = addShutdownHook;
    }

    @SuppressWarnings("unused")
    public EmbeddedCassandraInitializer() {
        this(true);
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            String storageDirPath = environment.getProperty("cassandra.storagedir");
            String storagePort = environment.getProperty("cassandra.storage_port");
            File storageDir = new File(storageDirPath);
            if (!storageDir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                storageDir.mkdirs();
            }
            String listenAddress = environment.getProperty("cassandra.listen_address");
            String nativeTransportPort = environment.getProperty("cassandra.native_transport_port");
            System.setProperty("cassandra.storagedir", storageDirPath);
            System.setProperty("cassandra.storage_port", storagePort);
            System.setProperty("cassandra.listen_address", listenAddress);
            System.setProperty("cassandra.native_transport_port", nativeTransportPort);
            EmbeddedCassandraServerHelper.startEmbeddedCassandra();
            if (addShutdownHook) {
                Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
            }
            Cluster cluster = Cluster.builder()
                    .withoutMetrics()
                    .addContactPoints(listenAddress).withPort(Integer.valueOf(nativeTransportPort)).build();
            Session session = cluster.connect();
            String keyspace = environment.getProperty("cassandra.keyspace");
            session.execute("CREATE KEYSPACE " + keyspace + " WITH replication "
                    + "= {'class':'SimpleStrategy', 'replication_factor':3};");
        } catch (Exception e) {
            throw new RuntimeException("Unable to initialize embedded Cassandra", e);
        }
    }

    public void shutdown() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }
}
