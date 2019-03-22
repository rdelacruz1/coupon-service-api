package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.cache.annotation.CachePut;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponByRegionRepository extends CassandraRepository<CouponByRegion, String> {
    @CachePut("couponsByRegion")
    Slice<CouponByRegion> findAllByRegionName(String regionName, Pageable pageable);

}
