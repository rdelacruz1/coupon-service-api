package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponByRegionRepository extends CassandraRepository<CouponByRegion, String> {
    List<CouponByRegion> findAllByRegionName(String regionName);

}
