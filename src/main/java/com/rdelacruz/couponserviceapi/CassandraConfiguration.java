package com.rdelacruz.couponserviceapi;

import com.datastax.driver.core.ServerSideTimestampGenerator;
import com.datastax.driver.core.TimestampGenerator;
import com.datastax.driver.core.policies.ExponentialReconnectionPolicy;
import com.datastax.driver.core.policies.ReconnectionPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories(
        basePackages = "com.rdelacruz.couponserviceapi")
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    private static final String[] ENTITY_BASE_PACKAGES = {"com.rdelacruz.couponserviceapi.domain"};

    @Value("${cassandra.listen_address}")
    private String listenAddress;

    @Value("${cassandra.native_transport_port}")
    private int nativeTransportPort;

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster =
                new CassandraClusterFactoryBean();
        cluster.setContactPoints(listenAddress);
        cluster.setPort(nativeTransportPort);
        cluster.setMetricsEnabled(false);
        return cluster;
    }

    @Override
    public String[] getEntityBasePackages() {
        return ENTITY_BASE_PACKAGES;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getContactPoints() {
        return listenAddress;
    }

    @Override
    protected boolean getMetricsEnabled() {
        return false;
    }

    @Override
    protected int getPort() {
        return nativeTransportPort;
    }

    @Override
    protected ReconnectionPolicy getReconnectionPolicy() {
        return new ExponentialReconnectionPolicy(100, 2000);
    }

    @Override
    protected TimestampGenerator getTimestampGenerator() {
        return ServerSideTimestampGenerator.INSTANCE;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(keyspace).withSimpleReplication(3));
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Collections.singletonList(DropKeyspaceSpecification.dropKeyspace(keyspace));
    }

    @Override
    protected List<String> getStartupScripts() {
        return readLinesFromResources("startup-data.sql");
    }

    private List<String> readLinesFromResources(String resourceName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(resourceName)))) {
            List<String> list = new ArrayList<>();
            while(reader.ready()) {
                String line = reader.readLine().trim();
                if (!(0==line.length() || line.startsWith("--"))) {
                    list.add(line);
                }
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<String> getShutdownScripts() {
        return readLinesFromResources("shutdown-data.sql");
    }
}
