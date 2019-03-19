package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusinessRepository extends CassandraRepository<Business, UUID> {
    Optional<Business> findByName(String name);
}
